package com.jachwirus.documentreadapi.config;

import com.jachwirus.documentreadapi.model.*;
import com.jachwirus.documentreadapi.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Date;

@Configuration
public class LoadDatabase {
    private final Environment environment;
    public LoadDatabase(Environment environment){
        this.environment = environment;
    }

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(
            DocumentRepository documentRepository,
            DocumentVersionRepository documentVersionRepository,
            DocumentHashTagRepository documentHashTagRepository,
            HashTagRepository hashTagRepository,
            CommentRepository commentRepository,
            UserRepository userRepository
    ) {
        final String env = environment.getActiveProfiles()[0];
        switch (env){
            case "dev":
                return args -> {
                    loadMockData(
                            documentRepository,
                            documentVersionRepository,
                            documentHashTagRepository,
                            hashTagRepository,
                            commentRepository,
                            userRepository
                    );
                };
            default:
                return args -> {};
        }
    }

    private void loadMockData(
            DocumentRepository documentRepository,
            DocumentVersionRepository documentVersionRepository,
            DocumentHashTagRepository documentHashTagRepository,
            HashTagRepository hashTagRepository,
            CommentRepository commentRepository,
            UserRepository userRepository
    ){
        User user = addUser(userRepository);
        Document document = new Document().setTitle("tmp1").setLikes(1).setDislikes(0).setViewCount(12).setCategory("laundry");
        documentRepository.save(document);

        addNewVersion(user, "http://qofmpxmytmrj4990290.cdn.ntruss.com/tmp.md", "tmp1Thumnail.com", 123, new Date(), null, document, documentRepository, documentVersionRepository);
        addNewVersion( user, "http://qofmpxmytmrj4990290.cdn.ntruss.com/tmp.html", "tmp2Thumnail.com", 12345, new Date(), null, document, documentRepository, documentVersionRepository);

        addHashTag("hello", document, documentHashTagRepository, hashTagRepository);
        addHashTag("world", document, documentHashTagRepository, hashTagRepository);

        addComments(user, "hihihi", document, documentRepository, commentRepository);
        addComments(user, "byebyebye", document, documentRepository, commentRepository);

        documentRepository.findAll().forEach(doc -> log.info("Preloaded" + doc));
    }

    private User addUser(UserRepository userRepository) {
        User user = new User()
                .setUserId("hello")
                .setPassword("world")
                .setNickname("Johnie");
        userRepository.save(user);
        return user;
    }

    private void addComments(
            User user,
            String contents,
            Document document,
            DocumentRepository documentRepository,
            CommentRepository commentRepository
    ) {
        Comment comment = new Comment()
                .setContents(contents)
                .setCreatedAt(new Date())
                .setModified(false);
        user.writeComment(comment);
        document.addComment(comment);
        commentRepository.save(comment);
        documentRepository.save(document);
    }

    private void addNewVersion(
            User user,
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
        user.contributeDocument(version);
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
