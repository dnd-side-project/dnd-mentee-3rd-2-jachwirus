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
            "INNER JOIN " +
            "DocumentVersion version " +
            "ON " +
            "doc.latestVersion.id = version.id " +
            "LEFT JOIN fetch " +
            "doc.comments")
    List<Document> findAllWithLatestVersion();

    @Query("SELECT doc " +
            "FROM Document doc " +
            "INNER JOIN " +
            "DocumentVersion version " +
            "ON " +
            "doc.latestVersion.id = version.id " +
            "LEFT JOIN fetch " +
            "doc.comments")
    List<Document> findAllWithLatestVersionAndAndComments(Sort sort);
}
