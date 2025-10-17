package com.SoloProject.solo.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "claim")
public class Claim {

    @Id
    @GeneratedValue
    private UUID id;
    private String claimNumber;         // human-friendly key for UI
    private UUID memberId;
    private UUID providerId;
    private LocalDate serviceStartDate;
    private LocalDate serviceEndDate;
    private LocalDate receivedDate;
    private BigDecimal totalBilled;
    private BigDecimal totalAllowed;
    private BigDecimal totalPlanPaid;
    private BigDecimal totalMemberResponsibility;
    private OffsetDateTime updatedAt;
    private String claimStatus;

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClaimLine> lines;

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClaimStatusEvent> statusHistory;

    public Claim() {}

    public Claim(UUID id, String claimNumber, UUID memberId, UUID providerId, LocalDate serviceStartDate,
                 LocalDate serviceEndDate, LocalDate receivedDate, BigDecimal totalBilled, BigDecimal totalAllowed,
                 BigDecimal totalPlanPaid, BigDecimal totalMemberResponsibility, List<ClaimLine> lines,
                 List<ClaimStatusEvent> statusHistory, OffsetDateTime updatedAt, String claimStatus){
        this.claimNumber = claimNumber;         // human-friendly key for UI
        this.memberId = memberId;
        this.providerId = providerId;
        this.serviceStartDate = serviceStartDate;
        this.serviceEndDate = serviceEndDate;
        this.receivedDate = receivedDate;
        this.totalBilled = totalBilled;
        this.totalAllowed = totalAllowed;
        this.totalPlanPaid = totalPlanPaid;
        this.totalMemberResponsibility = totalMemberResponsibility;
        this.lines = lines;
        this.statusHistory = statusHistory;
        this.updatedAt = updatedAt;
        this.claimStatus = claimStatus;
        setLines(lines);
        setStatusHistory(statusHistory);
}

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public void setProviderId(UUID providerId) {
        this.providerId = providerId;
    }

    public UUID getId() {
        return id;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }

    public UUID getProviderId() {
        return providerId;
    }

    public LocalDate getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(LocalDate serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    public LocalDate getServiceEndDate() {
        return serviceEndDate;
    }

    public void setServiceEndDate(LocalDate serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public BigDecimal getTotalBilled() {
        return totalBilled;
    }

    public void setTotalBilled(BigDecimal totalBilled) {
        this.totalBilled = totalBilled;
    }

    public BigDecimal getTotalAllowed() {
        return totalAllowed;
    }

    public void setTotalAllowed(BigDecimal totalAllowed) {
        this.totalAllowed = totalAllowed;
    }

    public BigDecimal getTotalPlanPaid() {
        return totalPlanPaid;
    }

    public void setTotalPlanPaid(BigDecimal totalPlanPaid) {
        this.totalPlanPaid = totalPlanPaid;
    }

    public BigDecimal getTotalMemberResponsibility() {
        return totalMemberResponsibility;
    }

    public void setTotalMemberResponsibility(BigDecimal totalMemberResponsibility) {
        this.totalMemberResponsibility = totalMemberResponsibility;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public List<ClaimLine> getLines() {return lines;}
    public void setLines(List<ClaimLine> lines) {
        this.lines.clear();
        if (lines != null) {
            lines.forEach(line -> line.setClaim(this));
            this.lines.addAll(lines);
        }
    }

    public List<ClaimStatusEvent> getStatusHistory() {return statusHistory;}
    public void setStatusHistory(List<ClaimStatusEvent> statusHistory) {
        this.statusHistory.clear();
        if (statusHistory != null) {
            statusHistory.forEach(event -> event.setClaim(this));
            this.statusHistory.addAll(statusHistory);
        }
    }
}
