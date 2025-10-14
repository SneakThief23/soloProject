package com.SoloProject.solo.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "user",
uniqueConstraints = @UniqueConstraint(columnNames = {"authProvider", "authSub"}))
public class User {

    @Id
    @GeneratedValue
    private UUID id;
    private String authProvider;     // e.g., "google", "okta"
    private String authSub;          // OIDC subject ("sub"), unique per provider

    @Column(unique = true)
    private String email;            // from ID token

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    private String firstName;
    private String lastName;

    public User() {}

    public User(String authProvider, String authSub, String email, OffsetDateTime createdAt, OffsetDateTime updatedAt, String firstName, String lastName) {
        this.authProvider = authProvider;
        this.authSub = authSub;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }

    public String getAuthSub() {
        return authSub;
    }

    public void setAuthSub(String authSub) {
        this.authSub = authSub;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}