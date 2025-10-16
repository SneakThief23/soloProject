package com.SoloProject.solo.service;

import com.SoloProject.solo.models.Claim;
import com.SoloProject.solo.models.ClaimLine;
import com.SoloProject.solo.models.ClaimStatusEvent;
import com.SoloProject.solo.repos.ClaimRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class ClaimService {

    private final ClaimRepo claimRepo;

    public ClaimService(ClaimRepo claimRepo) {
        this.claimRepo = claimRepo;
    }

    public List<Claim> getAllClaim() {
        return claimRepo.findAll();
    }

    public List<Claim> getClaimByMemberId(UUID memberId) {
        return claimRepo.findByMemberId(memberId);
    }

    public Claim createClaim(Claim claim) {
        return claimRepo.save(claim);
    }

    //Update existing claim
    public Optional<Claim> updateClaim(UUID id, Claim newData) {
        return claimRepo.findById(id).map(existing -> {
            if (newData.getClaimNumber () != null)existing.setClaimNumber(newData.getClaimNumber());
            if (newData.getMemberId () != null)existing.setMemberId(newData.getMemberId());
            if (newData.getProviderId () != null)existing.setProviderId(newData.getProviderId());
            if (newData.getServiceStartDate () != null)existing.setServiceStartDate(newData.getServiceStartDate());
            if (newData.getServiceEndDate () != null)existing.setServiceEndDate(newData.getServiceEndDate());
            if (newData.getReceivedDate () != null)existing.setReceivedDate(newData.getReceivedDate());
            if (newData.getTotalBilled () != null)existing.setTotalBilled(newData.getTotalBilled());
            if (newData.getTotalAllowed () != null)existing.setTotalAllowed(newData.getTotalAllowed());
            if (newData.getTotalPlanPaid () != null)existing.setTotalPlanPaid(newData.getTotalPlanPaid());
            if (newData.getTotalMemberResponsibility () != null)existing.setTotalMemberResponsibility(newData.getTotalMemberResponsibility());
            if (newData.getUpdatedAt () != null)existing.setUpdatedAt(newData.getUpdatedAt());
            if (newData.getClaimStatus () != null)existing.setClaimStatus(newData.getClaimStatus());

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

        public boolean deleteClaim(UUID id) {
            if (claimRepo.existsById(id)) {
                claimRepo.deleteById(id);
                return true;
            }
            return false;
        }
}
