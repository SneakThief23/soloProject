package com.SoloProject.solo.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue
    private UUID id;
    private UUID userId;           // FK -> User
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;          // optional profile display
    private String phone;          // optional profile display
    private Address mailingAddress; // optional embedded/owned
    private List<Enrollment> enrollments; // one active for current plan year

    public Member() {}

    public Member(UUID id, UUID userId, String firstName, String lastName, LocalDate dateOfBirth, String email, List<Enrollment> enrollments, String phone, Address mailingAddress) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.enrollments = enrollments;
        this.phone = phone;
        this.mailingAddress = mailingAddress;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
