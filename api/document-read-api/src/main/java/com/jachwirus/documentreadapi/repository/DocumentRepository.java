package com.jachwirus.documentreadapi.repository;

import com.jachwirus.documentreadapi.model.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByCategory(String category);

    @Query("SELECT doc " +
            "FROM Document doc " +
            "INNER JOIN fetch " +
            "doc.latestVersion " +
            "LEFT JOIN fetch " +
            "doc.tags as tags " +
            "INNER JOIN fetch " +
            "tags.hashTag")
    List<Document> findAll(Sort sort);
}