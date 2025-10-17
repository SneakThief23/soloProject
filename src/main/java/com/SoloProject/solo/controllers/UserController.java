package com.SoloProject.solo.controllers;

import com.SoloProject.solo.models.User;
import com.SoloProject.solo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Add OAuh login stuff here

    @GetMapping("/by-mail")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse((ResponseEntity.notFound().build()));
    }

    @PostMapping("/oauth-login")
    public ResponseEntity<User> oauthLogin(@RequestBody User oauthUser) {
        Optional<User> existingUser = userService.findByAuthProviderAndSub(
                oauthUser.getAuthProvider(),
                oauthUser.getAuthSub()
        );
        User user;
        if (existingUser.isPresent()) {
            user = userService.updateUserFromOAuth(existingUser.get(), oauthUser);
        } else {
            user = userService.createUser(oauthUser);
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User newData) {
        Optional<User> existingUser = userService.findById(id);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User updated = userService.updateUserFromOAuth(existingUser.get(), newData);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
