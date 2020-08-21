package com.jachwirus.documentreadapi.repository;

import com.jachwirus.documentreadapi.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByCategory(String category);
}
