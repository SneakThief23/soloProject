package com.SoloProject.solo.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "provider")
public class Provider {

    @Id
    @GeneratedValue
    UUID id;
    String name;
    String specialty;
    Address address;
    String phone;

    public Provider(UUID id, String name, String specialty, Address address, String phone) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.address = address;
        this.phone = phone;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
