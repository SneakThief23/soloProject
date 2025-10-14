package com.SoloProject.solo.repos;

import com.SoloProject.solo.models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClaimRepo extends JpaRepository<Claim, UUID>{
}
