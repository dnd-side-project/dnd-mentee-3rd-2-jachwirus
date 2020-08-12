package com.jachwirus.documentreadapi.repository;

import com.jachwirus.documentreadapi.dto.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> { }
