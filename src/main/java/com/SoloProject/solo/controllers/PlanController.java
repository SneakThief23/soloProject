package com.SoloProject.solo.controllers;

import com.SoloProject.solo.models.Plan;
import com.SoloProject.solo.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    public List<Plan> getAllPlans(UUID id) {
        return planService.getAllPlan();
    }

    @GetMapping("/user/{id}")
    public List<Plan> getPlansByUser(@PathVariable("id") UUID id) {
        return planService.getPlanByMemberId(id);
    }

    @GetMapping("/user/me")
    public ResponseEntity<List<Plan>> getPlansForCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        List<Plan> plans = planService.getPlansForUser(userDetails.getUsername());
        return ResponseEntity.ok(plans);
    }

    @PostMapping
    public Plan createPlan(@RequestBody Plan plan) {
        return planService.createPlan(plan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plan> updatePlan(@PathVariable UUID id, @RequestBody Plan plan) {
        Optional<Plan> updated = planService.updatePlan(id, plan);
        return updated
                .map(acc -> ResponseEntity.ok(acc))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable UUID id) {
        boolean deleted = planService.deletePlan(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

