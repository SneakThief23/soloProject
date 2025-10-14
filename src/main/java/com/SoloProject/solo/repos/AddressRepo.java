package com.SoloProject.solo.repos;

import com.SoloProject.solo.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepo extends JpaRepository<Address, UUID> {
}