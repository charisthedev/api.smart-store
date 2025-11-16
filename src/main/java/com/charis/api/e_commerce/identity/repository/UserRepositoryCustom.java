package com.charis.api.e_commerce.identity.repository;

import com.charis.api.e_commerce.identity.domain.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findByEmail(String email);
}
