package com.jachwirus.documentreadapi.util;

import com.jachwirus.documentreadapi.model.*;
import com.jachwirus.documentreadapi.repository.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MockDataHandler {
    private DocumentRepository documentRepository;
    private DocumentVersionRepository documentVersionRepository;
    private DocumentHashTagRepository documentHashTagRepository;
    private HashTagRepository hashTagRepository;
    private CommentRepository commentRepository;
    private List<Document> mockData;

    private MockDataHandler(
            DocumentRepository documentRepository,
            DocumentVersionRepository documentVersionRepository,
            DocumentHashTagRepository documentHashTagRepository,
            HashTagRepository hashTagRepository,
            CommentRepository commentRepository
    ) {
        this.documentRepository = documentRepository;
        this.documentVersionRepository = documentVersionRepository;
        this.documentHashTagRepository = documentHashTagRepository;
        this.hashTagRepository = hashTagRepository;
        this.commentRepository = commentRepository;
        this.mockData = new ArrayList<>();
        setMockDocumentModel();
    }

    public static MockDataHandler instance;

    public static MockDataHandler getInstance(
            DocumentRepository documentRepository,
            DocumentVersionRepository documentVersionRepository,
            DocumentHashTagRepository documentHashTagRepository,
            HashTagRepository hashTagRepository,
            CommentRepository commentRepository
    ){
        if(instance == null) {
            instance = new MockDataHandler(
                    documentRepository,
                    documentVersionRepository,
                    documentHashTagRepository,
                    hashTagRepository,
                    commentRepository
            );
        }
        return instance;
    }

    public List<Document> getMockDataFilterBy(String category) {
        List<Document> list = this.mockData.stream()
                .filter(data -> data.getCategory() == category)
                .collect(Collectors.toList());
        return list;
    }

    public List<Document> getMockDataSortBy(String sortTarget) {
        Comparator<Document> comparator = getComparator(sortTarget);
        List<Document> list = this.mockData.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        return list;
    }

    public List<Document> getMockDataList() {
        return this.mockData;
    }

    public Document getMockData(long id) {
        Document data = this.mockData.stream()
                .filter(doc -> doc.getId() == id)
                .collect(Collectors.toList())
                .get(0);
        return data;
    }

    private Comparator<Document> getComparator(String sortTarget) {
        if(sortTarget.equals("latest")) {
            return (o1, o2) -> compareLong(o2.getId(), o1.getId());
        } else if(sortTarget.equals("popularity")) {
            return ((o1, o2) -> {
                if(o2.getViewCount() == o1.getViewCount()) {
                    return compareLong(o2.getId(), o1.getId());
                }else {
                    return o2.getViewCount() - o1.getViewCount();
                }
            });
        } else {
            return null;
        }
    }

    private int compareLong(long a, long b) {
        return a > b ? 1 : -1;
    }

    public void setMockDocumentModel() {
        Document document = new Document().setTitle("tmp1").setLikes(1).setDislikes(0).setViewCount(12).setCategory("laundry");
        documentRepository.save(document);

        addNewVersion("http://qofmpxmytmrj4990290.cdn.ntruss.com/tmp.md", "tmp1Thumnail.com", 123, new Date(), null, document, documentRepository, documentVersionRepository);
        addNewVersion( "http://qofmpxmytmrj4990290.cdn.ntruss.com/tmp.html", "tmp2Thumnail.com", 12345, new Date(), null, document, documentRepository, documentVersionRepository);

        addHashTag("hello", document, documentHashTagRepository, hashTagRepository);
        addHashTag("world", document, documentHashTagRepository, hashTagRepository);

        addComments("hihihi", document, documentRepository, commentRepository);
        addComments("byebyebye", document, documentRepository, commentRepository);

        this.mockData.add(document);

        document = new Document().setTitle("tmp2").setLikes(1).setDislikes(0).setViewCount(1).setCategory("cafe");
        documentRepository.save(document);

        addNewVersion("http://qofmpxmytmrj4990290.cdn.ntruss.com/tmp.md", "tmp1Thumnail.com", 123, new Date(), null, document, documentRepository, documentVersionRepository);
        addNewVersion( "http://qofmpxmytmrj4990290.cdn.ntruss.com/tmp.html", "tmp2Thumnail.com", 12345, new Date(), null, document, documentRepository, documentVersionRepository);

        addHashTag("hello", document, documentHashTagRepository, hashTagRepository);
        addHashTag("world", document, documentHashTagRepository, hashTagRepository);

        addComments("hihihi", document, documentRepository, commentRepository);
        addComments("byebyebye", document, documentRepository, commentRepository);

        this.mockData.add(document);

        document = new Document().setTitle("tmp3").setLikes(1).setDislikes(0).setViewCount(5).setCategory("cafe");
        documentRepository.save(document);

        addNewVersion("http://qofmpxmytmrj4990290.cdn.ntruss.com/tmp.md", "tmp1Thumnail.com", 123, new Date(), null, document, documentRepository, documentVersionRepository);
        addNewVersion( "http://qofmpxmytmrj4990290.cdn.ntruss.com/tmp.html", "tmp2Thumnail.com", 12345, new Date(), null, document, documentRepository, documentVersionRepository);

        addHashTag("hello", document, documentHashTagRepository, hashTagRepository);
        addHashTag("world", document, documentHashTagRepository, hashTagRepository);

        addComments("hihihi", document, documentRepository, commentRepository);
        addComments("byebyebye", document, documentRepository, commentRepository);

        this.mockData.add(document);

        document = new Document().setTitle("tmp4").setLikes(1).setDislikes(0).setViewCount(1).setCategory("cafe");
        documentRepository.save(document);

        addNewVersion("http://qofmpxmytmrj4990290.cdn.ntruss.com/tmp.md", "tmp1Thumnail.com", 123, new Date(), null, document, documentRepository, documentVersionRepository);
        addNewVersion( "http://qofmpxmytmrj4990290.cdn.ntruss.com/tmp.html", "tmp2Thumnail.com", 12345, new Date(), null, document, documentRepository, documentVersionRepository);

        addHashTag("hello", document, documentHashTagRepository, hashTagRepository);
        addHashTag("world", document, documentHashTagRepository, hashTagRepository);

        addComments("hihihi", document, documentRepository, commentRepository);
        addComments("byebyebye", document, documentRepository, commentRepository);

        this.mockData.add(document);
    }

    private void addComments(
            String contents,
            Document document,
            DocumentRepository documentRepository,
            CommentRepository commentRepository
    ) {
        Comment comment = new Comment()
                .setContents(contents)
                .setCreatedAt(new Date())
                .setModified(false);
        document.addComment(comment);
        commentRepository.save(comment);
        documentRepository.save(document);
    }

    private void addNewVersion(
            String dataUrl,
            String thumbnailUrl,
            int flag,
            Date createdAt,
            String diff,
            Document document,
            DocumentRepository documentRepository,
            DocumentVersionRepository documentVersionRepository
    ) {
        DocumentVersion version = new DocumentVersion()
                .setDataUrl(dataUrl)
                .setThumbnailUrl(thumbnailUrl)
                .setFlag(flag)
                .setCreatedAt(createdAt)
                .setDiff(diff);
        document.addNewVersion(version);
        documentVersionRepository.save(version);
        documentRepository.save(document);
    }

    private void addHashTag(
            String tagName,
            Document document,
            DocumentHashTagRepository documentHashTagRepository,
            HashTagRepository hashTagRepository
    ) {
        HashTag tag = getHashTag(hashTagRepository, tagName);
        DocumentHashTag mapping = new DocumentHashTag();

        document.addHashTag(mapping, tag);
        hashTagRepository.save(tag);
        documentHashTagRepository.save(mapping);
    }

    private HashTag getHashTag(HashTagRepository repository, String tagName) {
        HashTag tag = repository.findByTagName(tagName)
                .orElse(new HashTag().setTagName(tagName));
        return tag;
    }
}
