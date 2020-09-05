package com.jachwirus.documentreadapi.repository;

import com.jachwirus.documentreadapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
