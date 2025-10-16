package com.SoloProject.solo.service;

import com.SoloProject.solo.models.Plan;
import com.SoloProject.solo.repos.PlanRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlanService {
    private final PlanRepo planRepo;

    public PlanService(PlanRepo planRepo) {
        this.planRepo = planRepo;
    }

    public List<Plan> getAllPlan() {
        return planRepo.findAll();
    }

    public List<Plan> getPlanByMemberId(UUID memberId) {
        return planRepo.findByMemberId(memberId);
    }

    public Plan createPlan(Plan plan) {
        return planRepo.save(plan);
    }
    // Update existing plan
    public Optional<Plan> updatePlan(UUID id, Plan newData) {
        return planRepo.findById(id).map(existing -> {
            if (newData.getName () != null)existing.setName(newData.getName());
            if (newData.getPlanType () != null)existing.setPlanType(newData.getPlanType());
            if (newData.getNetworkName () != null)existing.setNetworkName(newData.getNetworkName());
            if (newData.getPlanYear () != null)existing.setPlanYear(newData.getPlanYear());
            return planRepo.save(existing);
        });
    }
    public boolean deletePlan(UUID id) {
        if (planRepo.existsById(id)) {
            planRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
