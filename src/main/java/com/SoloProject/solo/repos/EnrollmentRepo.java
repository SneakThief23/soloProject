package com.SoloProject.solo.repos;

import com.SoloProject.solo.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnrollmentRepo extends JpaRepository<Enrollment, UUID> {
    Enrollment findByMemberId(UUID memberId);
}
