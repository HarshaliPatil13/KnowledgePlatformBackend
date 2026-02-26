package com.knowledgeplatform.knowledgeplatform.service;

import com.knowledgeplatform.knowledgeplatform.entity.User;
import java.util.Optional;

public interface UserService {

    User registerUser(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);
}
