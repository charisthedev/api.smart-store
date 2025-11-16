package com.charis.api.e_commerce.identity.domain;

import com.charis.api.e_commerce.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone_number;

    @Column(nullable = false)
    private String password_hash;

    @Column()
    private Instant email_verified_at;

    @Column()
    private Instant date_of_birth;

    @Column(nullable = false,columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean is_active;


    @Column(nullable = false,columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean is_deleted;

    @Column()
    private Instant deleted_at;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,columnDefinition = "varchar(20) default 'USER'")
    private UserRole role ;
}
