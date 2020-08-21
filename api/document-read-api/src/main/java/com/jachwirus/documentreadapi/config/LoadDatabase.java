package com.jachwirus.documentreadapi.config;

import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.model.DocumentVersion;
import com.jachwirus.documentreadapi.repository.DocumentRepository;
import com.jachwirus.documentreadapi.repository.DocumentVersionRepository;
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
            DocumentVersionRepository documentVersionRepository
    ) {
        final String env = environment.getActiveProfiles()[0];
        switch (env){
            case "dev":
                return args -> {
                    loadMockData(documentRepository, documentVersionRepository);
                };
            default:
                return args -> {};
        }
    }

    private void loadMockData(
            DocumentRepository documentRepository,
            DocumentVersionRepository documentVersionRepository
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

        DocumentVersion version = new DocumentVersion()
                .setContributorId(contributorId)
                .setDataUrl(dataUrl)
                .setThumbnailUrl(thumbnailUrl)
                .setFlag(flag)
                .setCreatedAt(createdAt)
                .setDiff(diff);

        document.addNewVersion(version);
        documentVersionRepository.save(version);

        contributorId = 2;
        dataUrl="tmp2.com"; ;
        thumbnailUrl="tmp2Thumnail.com";
        flag = 12345;
        createdAt = new Date();
        diff = null;

        DocumentVersion version2 = new DocumentVersion()
                .setContributorId(contributorId)
                .setDataUrl(dataUrl)
                .setThumbnailUrl(thumbnailUrl)
                .setFlag(flag)
                .setCreatedAt(createdAt)
                .setDiff(diff);

        document.addNewVersion(version2);
        documentVersionRepository.save(version2);

        documentRepository.findAll().forEach(doc -> log.info("Preloaded" + doc));
    }

}