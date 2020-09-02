package com.jachwirus.documentreadapi.repository;

import com.jachwirus.documentreadapi.model.Document;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("SELECT doc " +
            "FROM Document doc " +
            "INNER JOIN fetch " +
            "doc.latestVersion " +
            "LEFT JOIN fetch " +
            "doc.tags as tags " +
            "INNER JOIN fetch " +
            "tags.hashTag " +
            "WHERE " +
            "doc.category = ?1 " +
            "ORDER BY " +
            "doc.id DESC")
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
            "doc.id DESC")
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

    @Query("SELECT doc " +
            "FROM Document doc "+
            "INNER JOIN fetch " +
            "doc.latestVersion " +
            "LEFT JOIN fetch " +
            "doc.tags as tags " +
            "INNER JOIN fetch " +
            "tags.hashTag " +
            "WHERE " +
            "doc.id = ?1")
    Optional<Document> findById(Long id);
}