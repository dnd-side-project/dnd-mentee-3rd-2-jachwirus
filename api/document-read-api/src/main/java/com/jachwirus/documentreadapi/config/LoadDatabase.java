package com.jachwirus.documentreadapi.config;

import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.model.DocumentHashTag;
import com.jachwirus.documentreadapi.model.DocumentVersion;
import com.jachwirus.documentreadapi.model.HashTag;
import com.jachwirus.documentreadapi.repository.DocumentHashTagRepository;
import com.jachwirus.documentreadapi.repository.DocumentRepository;
import com.jachwirus.documentreadapi.repository.DocumentVersionRepository;
import com.jachwirus.documentreadapi.repository.HashTagRepository;
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
            HashTagRepository hashTagRepository
    ) {
        final String env = environment.getActiveProfiles()[0];
        switch (env){
            case "dev":
                return args -> {
                    loadMockData(
                            documentRepository,
                            documentVersionRepository,
                            documentHashTagRepository,
                            hashTagRepository
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
            HashTagRepository hashTagRepository
    ){
        String title, category;
        int like, dislike, view_count;

        String dataUrl, thumbnailUrl, diff;
        long contributorId;
        int flag;
        Date createdAt;

        title = "tmp1";
        like=1;
        dislike=0;
        view_count = 12;
        category="laundry";

        Document document = new Document()
                .setTitle(title).setLikes(like).setDislikes(dislike)
                .setViewCount(view_count).setCategory(category);
        documentRepository.save(document);

        contributorId = 1;
        dataUrl="tmp1.com"; ;
        thumbnailUrl="tmp1Thumnail.com";
        flag = 123;
        createdAt = new Date();
        diff = null;

        addNewVersion(
                contributorId,
                dataUrl,
                thumbnailUrl,
                flag,
                createdAt,
                diff,
                document,
                documentVersionRepository
        );

        contributorId = 2;
        dataUrl="tmp2.com"; ;
        thumbnailUrl="tmp2Thumnail.com";
        flag = 12345;
        createdAt = new Date();
        diff = null;

        addNewVersion(
                contributorId,
                dataUrl,
                thumbnailUrl,
                flag,
                createdAt,
                diff,
                document,
                documentVersionRepository
        );

        String tagName = "hello";
        addHashTag(tagName, document, documentHashTagRepository, hashTagRepository);

        String tagName2 = "world";
        addHashTag(tagName2, document, documentHashTagRepository, hashTagRepository);

        documentRepository.findAll().forEach(doc -> log.info("Preloaded" + doc));
    }

    private void addNewVersion(
            long contributorId,
            String dataUrl,
            String thumbnailUrl,
            int flag,
            Date createdAt,
            String diff,
            Document document,
            DocumentVersionRepository documentVersionRepository
    ) {
        DocumentVersion version = new DocumentVersion()
                .setContributorId(contributorId)
                .setDataUrl(dataUrl)
                .setThumbnailUrl(thumbnailUrl)
                .setFlag(flag)
                .setCreatedAt(createdAt)
                .setDiff(diff);
        document.addNewVersion(version);
        documentVersionRepository.save(version);
    }

    private void addHashTag(
            String tagName,
            Document document,
            DocumentHashTagRepository documentHashTagRepository,
            HashTagRepository hashTagRepository
    ) {
        HashTag tag = new HashTag().setTagName(tagName);
        DocumentHashTag mapping = new DocumentHashTag();

        document.addHashTag(mapping, tag);
        hashTagRepository.save(tag);
        documentHashTagRepository.save(mapping);
    }

}
//Caused by: org.hibernate.LazyInitializationException:
// failed to lazily initialize a collection of role:
// com.jachwirus.documentreadapi.model.Document.comments, could not initialize proxy
// - no Session
//	at org.hibernate.collection.internal.AbstractPersistentCollection.throwLazyInitializationException(AbstractPersistentCollection.java:606) ~[hibernate-core-5.4.18.Final.jar:5.4.18.Final]
//	at org.hibernate.collection.internal.AbstractPersistentCollection.withTemporarySessionIfNeeded(AbstractPersistentCollection.java:218) ~[hibernate-core-5.4.18.Final.jar:5.4.18.Final]
//	at org.hibernate.collection.internal.AbstractPersistentCollection.initialize(AbstractPersistentCollection.java:585) ~[hibernate-core-5.4.18.Final.jar:5.4.18.Final]
//	at org.hibernate.collection.internal.AbstractPersistentCollection.read(AbstractPersistentCollection.java:149) ~[hibernate-core-5.4.18.Final.jar:5.4.18.Final]
//	at org.hibernate.collection.internal.PersistentBag.toString(PersistentBag.java:621) ~[hibernate-core-5.4.18.Final.jar:5.4.18.Final]