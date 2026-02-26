package com.knowledgeplatform.knowledgeplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.knowledgeplatform.knowledgeplatform.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}