package com.SoloProject.solo.repos;

import com.SoloProject.solo.models.Accumulator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccumulatorRepo extends JpaRepository<Accumulator, UUID>{
    Optional<Accumulator> findByEnrollmentId (UUID enrollmentId);
}
