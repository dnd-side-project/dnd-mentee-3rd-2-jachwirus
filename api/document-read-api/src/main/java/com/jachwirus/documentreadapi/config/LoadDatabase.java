package com.jachwirus.documentreadapi.config;

import com.jachwirus.documentreadapi.dto.Document;
import com.jachwirus.documentreadapi.repository.DocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class LoadDatabase {
    private final Environment environment;
    public LoadDatabase(Environment environment){
        this.environment = environment;
    }

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(DocumentRepository repository) {
        final String env = environment.getActiveProfiles()[0];
        switch (env){
            case "dev":
                return args -> loadMockData(repository);
            default:
                return args -> {};
        }
    }

    private void loadMockData(DocumentRepository repository){
        String title,dataURL, thumbnailURL, category;
        int like, dislike, view_count;
        long last_version_id;

        title = "tmp1"; dataURL="tmp1.com"; like=1; dislike=0;
        thumbnailURL="tmp1Thumnail.com"; last_version_id=0;
        view_count = 12; category="laundry";
        repository.save(
                new Document(title, dataURL, like, dislike, thumbnailURL, last_version_id, view_count, category)
        );

        title = "tmp2"; dataURL="tmp2.com"; like=11; dislike=4;
        thumbnailURL="tmp2Thumnail.com"; last_version_id=6;
        view_count = 78; category="cafe";
        repository.save(
                new Document(title, dataURL, like, dislike, thumbnailURL, last_version_id, view_count, category)
        );

        repository.findAll().forEach(doc -> log.info("Preloaded" + doc));
    }

}