package com.jachwirus.documentreadapi.repository;

import com.jachwirus.documentreadapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
