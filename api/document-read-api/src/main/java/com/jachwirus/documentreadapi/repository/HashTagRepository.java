package com.jachwirus.documentreadapi.repository;

import com.jachwirus.documentreadapi.model.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    @Query("SELECT " +
            "tag " +
            "FROM HashTag tag " +
            "LEFT JOIN fetch " +
            "tag.documents")
    Optional<HashTag> findByTagName(String name);
}
