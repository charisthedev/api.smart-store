package com.charis.api.e_commerce.identity.service;

import com.charis.api.e_commerce.common.exceptions.ResourceNotFoundException;
import com.charis.api.e_commerce.identity.domain.User;
import com.charis.api.e_commerce.identity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public User getUserById(UUID id) {
        return userRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User Does not Exist"));
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User Does not Exist"));
    }

    public User createUser(User user) {
        user.setPassword_hash(passwordEncoder.encode(user.getPassword_hash()));
        return userRepo.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }
}
