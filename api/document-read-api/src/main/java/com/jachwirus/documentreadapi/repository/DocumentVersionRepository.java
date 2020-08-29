package com.jachwirus.documentreadapi.repository;

import com.jachwirus.documentreadapi.model.DocumentVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentVersionRepository extends JpaRepository<DocumentVersion, Long> {
}
