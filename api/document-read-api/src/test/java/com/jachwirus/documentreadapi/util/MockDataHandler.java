package com.jachwirus.documentreadapi.util;

import com.jachwirus.documentreadapi.model.*;
import com.jachwirus.documentreadapi.repository.*;
import java.util.Date;

public class MockDataHandler {
    private DocumentRepository documentRepository;
    private DocumentVersionRepository documentVersionRepository;
    private DocumentHashTagRepository documentHashTagRepository;
    private HashTagRepository hashTagRepository;
    private CommentRepository commentRepository;

    public MockDataHandler(
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

        getMockDocumentModel();
    }

    public Document getMockDocumentModel() {
        Document document = new Document().setTitle("tmp1").setLikes(1).setDislikes(0).setViewCount(12).setCategory("laundry");
        documentRepository.save(document);

        addNewVersion("http://qofmpxmytmrj4990290.cdn.ntruss.com/tmp.md", "tmp1Thumnail.com", 123, new Date(), null, document, documentRepository, documentVersionRepository);
        addNewVersion( "http://qofmpxmytmrj4990290.cdn.ntruss.com/tmp.html", "tmp2Thumnail.com", 12345, new Date(), null, document, documentRepository, documentVersionRepository);

        addHashTag("hello", document, documentHashTagRepository, hashTagRepository);
        addHashTag("world", document, documentHashTagRepository, hashTagRepository);

        addComments("hihihi", document, documentRepository, commentRepository);
        addComments("byebyebye", document, documentRepository, commentRepository);


        return document;
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
