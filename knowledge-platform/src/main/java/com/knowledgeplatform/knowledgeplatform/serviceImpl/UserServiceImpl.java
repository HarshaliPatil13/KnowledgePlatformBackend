package com.knowledgeplatform.knowledgeplatform.serviceImpl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import com.knowledgeplatform.knowledgeplatform.entity.User;
import com.knowledgeplatform.knowledgeplatform.repository.UserRepository;
import com.knowledgeplatform.knowledgeplatform.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}