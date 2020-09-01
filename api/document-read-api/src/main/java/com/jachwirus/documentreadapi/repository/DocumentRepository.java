package com.jachwirus.documentreadapi.repository;

import com.jachwirus.documentreadapi.model.Document;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByCategory(String category, Pageable pageable);

    @Query("SELECT doc " +
            "FROM Document doc " +
            "INNER JOIN fetch " +
            "doc.latestVersion " +
            "LEFT JOIN fetch " +
            "doc.tags as tags " +
            "INNER JOIN fetch " +
            "tags.hashTag " +
            "ORDER BY " +
            "doc.id DESC ")
    List<Document> findAllOrderByLatest(Pageable pageable);

    @Query("SELECT doc " +
            "FROM Document doc " +
            "INNER JOIN fetch " +
            "doc.latestVersion " +
            "LEFT JOIN fetch " +
            "doc.tags as tags " +
            "INNER JOIN fetch " +
            "tags.hashTag " +
            "ORDER BY " +
            "doc.viewCount DESC, doc.id DESC")
    List<Document> findAllOrderByPopularity(Pageable pageable);
}