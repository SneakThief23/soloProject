package com.SoloProject.solo.service;

import com.SoloProject.solo.models.Claim;
import com.SoloProject.solo.models.ClaimLine;
import com.SoloProject.solo.models.ClaimStatusEvent;
import com.SoloProject.solo.repos.ClaimRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClaimService {

    private final ClaimRepo claimRepo;

    public ClaimService(ClaimRepo claimRepo) {
        this.claimRepo = claimRepo;
    }

    //Update existing claim
    public Optional<Claim> updateClaim(UUID id, Claim newData) {
        return claimRepo.findById(id).map(existing -> {
            existing.setClaimNumber(newData.getClaimNumber());
            existing.setMemberId(newData.getMemberId());
            existing.setProviderId(newData.getProviderId());
            existing.setServiceStartDate(newData.getServiceStartDate());
            existing.setServiceEndDate(newData.getServiceEndDate());
            existing.setReceivedDate(newData.getReceivedDate());
            existing.setTotalBilled(newData.getTotalBilled());
            existing.setTotalAllowed(newData.getTotalAllowed());
            existing.setTotalPlanPaid(newData.getTotalPlanPaid());
            existing.setTotalMemberResponsibility(newData.getTotalMemberResponsibility());
            existing.setUpdatedAt(newData.getUpdatedAt());
            existing.setClaimStatus(newData.getClaimStatus());

            existing.getLines().clear();
            if (newData.getLines() != null) {
                for (ClaimLine line : newData.getLines()) {
                    line.setClaim(existing);
                    existing.getLines().add(line);
                }
            }

            existing.getStatusHistory().clear();
            if (newData.getStatusHistory() != null) {
                for (ClaimStatusEvent event : newData.getStatusHistory()) {
                    event.setClaim(existing);
                    existing.getStatusHistory().add(event);
                }
            }

            return claimRepo.save(existing);
        });
    }
}