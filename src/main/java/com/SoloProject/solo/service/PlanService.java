package com.SoloProject.solo.service;

import com.SoloProject.solo.models.Plan;
import com.SoloProject.solo.models.User;
import com.SoloProject.solo.repos.PlanRepo;
import com.SoloProject.solo.repos.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlanService {
    private final PlanRepo planRepo;
    private final UserRepo userRepo;

    public PlanService(PlanRepo planRepo, UserRepo userRepo) {
        this.planRepo = planRepo;
        this.userRepo = userRepo;
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

    public List<Plan> getPlansForUser(String email) {
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found: " + email);
        }
        User user = userOpt.get();
        return planRepo.findByMemberId(user.getId());
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