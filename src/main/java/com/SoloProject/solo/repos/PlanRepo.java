package com.SoloProject.solo.repos;

import com.SoloProject.solo.models.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PlanRepo extends JpaRepository<Plan, UUID> {
    List<Plan> findByMemberId(UUID memberId);
}