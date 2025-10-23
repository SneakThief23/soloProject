package com.SoloProject.solo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ClaimListDTO {

    private UUID id;
    private String claimNumber;
    private UUID memberId;
    private String memberName;  // joined from member table
    private UUID providerId;
    private String providerName;  // joined from provider table
    private LocalDate serviceStartDate;
    private LocalDate serviceEndDate;
    private LocalDate receivedDate;
    private BigDecimal totalBilled;
    private BigDecimal totalAllowed;
    private BigDecimal totalPlanPaid;
    private BigDecimal totalMemberResponsibility;
    private OffsetDateTime updatedAt;
    private String claimStatus;

    public ClaimListDTO() {}

    public ClaimListDTO(UUID id, String claimNumber, UUID memberId, String memberName,
                        UUID providerId, String providerName, LocalDate serviceStartDate,
                        LocalDate serviceEndDate, LocalDate receivedDate, BigDecimal totalBilled,
                        BigDecimal totalAllowed, BigDecimal totalPlanPaid,
                        BigDecimal totalMemberResponsibility, OffsetDateTime updatedAt,
                        String claimStatus) {
        this.id = id;
        this.claimNumber = claimNumber;
        this.memberId = memberId;
        this.memberName = memberName;
        this.providerId = providerId;
        this.providerName = providerName;
        this.serviceStartDate = serviceStartDate;
        this.serviceEndDate = serviceEndDate;
        this.receivedDate = receivedDate;
        this.totalBilled = totalBilled;
        this.totalAllowed = totalAllowed;
        this.totalPlanPaid = totalPlanPaid;
        this.totalMemberResponsibility = totalMemberResponsibility;
        this.updatedAt = updatedAt;
        this.claimStatus = claimStatus;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public UUID getProviderId() {
        return providerId;
    }

    public void setProviderId(UUID providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
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
}
