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
        view_count = 1;
        category="laundry";

        Document document = new Document()
                .setTitle(title).setLikes(like).setDislikes(dislike)
                .setViewCount(view_count).setCategory(category);
        documentRepository.save(document);

        contributorId = 1;
        dataUrl="http://qofmpxmytmrj4990290.cdn.ntruss.com/tmp.md"; ;
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
                documentRepository,
                documentVersionRepository
        );

        contributorId = 2;
        dataUrl="http://qofmpxmytmrj4990290.cdn.ntruss.com/tmp.html"; ;
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
                documentRepository,
                documentVersionRepository
        );

        String tagName = "hello";
        addHashTag(tagName, document, documentHashTagRepository, hashTagRepository);

        String tagName2 = "world";
        addHashTag(tagName2, document, documentHashTagRepository, hashTagRepository);

        documentRepository.findAllWithLatestVersion().forEach(doc -> log.info("Preloaded" + doc));
    }

    private void addNewVersion(
            long contributorId,
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
