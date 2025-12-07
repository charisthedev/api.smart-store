package com.charis.api.e_commerce.identity.service;

import com.charis.api.e_commerce.common.exceptions.ResourceNotFoundException;
import com.charis.api.e_commerce.identity.domain.User;
import com.charis.api.e_commerce.identity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;

    public User getUserById(UUID id) {
        return userRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User Does not Exist"));
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User Does not Exist"));
    }

    public User createUser(@NotNull User user) {
        return userRepo.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }
}
