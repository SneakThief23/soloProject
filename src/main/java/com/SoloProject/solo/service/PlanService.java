package com.SoloProject.solo.service;

import com.SoloProject.solo.models.Plan;
import com.SoloProject.solo.repos.PlanRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PlanService {
    private final PlanRepo planRepo;

    public PlanService(PlanRepo planRepo) {
        this.planRepo = planRepo;
    }
    // Update existing plan
    public Optional<Plan> updatePlan(UUID id, Plan newData) {
        return planRepo.findById(id).map(existing -> {
            existing.setName(newData.getName());
            existing.setPlanType(newData.getPlanType());
            existing.setNetworkName(newData.getNetworkName());
            existing.setPlanYear(newData.getPlanYear());
            return planRepo.save(existing);
        });
    }
}
