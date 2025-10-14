package com.SoloProject.solo.service;

import com.SoloProject.solo.models.User;
import com.SoloProject.solo.repos.UserRepo;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //Find a user by email
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    // Create a new user
    public User createUser(User user) {
        OffsetDateTime now = OffsetDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        return userRepo.save(user);
    }

    // Update existing user
    public User updateUserFromOAuth(User existing, User oauthData) {
        if (oauthData.getFirstName() != null) existing.setFirstName(oauthData.getFirstName());
        if (oauthData.getLastName() != null) existing.setLastName(oauthData.getLastName());
        if (oauthData.getEmail() != null) existing.setEmail(oauthData.getEmail());
        existing.setUpdatedAt(OffsetDateTime.now());
        return userRepo.save(existing);
    }

    public Optional<User> findByAuthProviderAndSub(String authProvider, String authSub){
        return userRepo.findByAuthProviderAndAuthSub(authProvider, authSub);
    }

    public Optional<User> findById(UUID id) {
        return userRepo.findById(id);
    }

    // Delete a user by ID
    public boolean deleteUser(UUID id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
