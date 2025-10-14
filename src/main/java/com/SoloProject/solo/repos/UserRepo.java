package com.SoloProject.solo.repos;

import com.SoloProject.solo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByAuthProviderAndAuthSub(String authProvider, String authSub);
}