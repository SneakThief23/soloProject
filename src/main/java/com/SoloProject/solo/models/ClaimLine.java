package com.SoloProject.solo.models;

import jakarta.persistence.*;

import java.util.UUID;
import java.math.BigDecimal;

@Entity
@Table(name = "ClaimLine")
public class ClaimLine {

    @Id
    @GeneratedValue
    UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claim_id", nullable = false)
    private Claim claim;

    private Integer lineNumber;         // 1..n
    private String dptCode;             // e.g., "99213"
    private String description;
    private BigDecimal billedAmount;
    private BigDecimal allowedAmount;
    private BigDecimal deductibleApplied;
    private BigDecimal copayApplied;
    private BigDecimal coinsuranceApplied;
    private BigDecimal planPaid;
    private BigDecimal memberResponsibility;

    public ClaimLine() {}

    public ClaimLine(UUID id, Integer lineNumber, String dptCode, String description, BigDecimal billedAmount, BigDecimal allowedAmount, BigDecimal deductibleApplied, BigDecimal copayApplied, BigDecimal coinsuranceApplied, BigDecimal planPaid, BigDecimal memberResponsibility) {
        this.id = id;
        this.lineNumber = lineNumber;
        this.dptCode = dptCode;
        this.description = description;
        this.billedAmount = billedAmount;
        this.allowedAmount = allowedAmount;
        this.deductibleApplied = deductibleApplied;
        this.copayApplied = copayApplied;
        this.coinsuranceApplied = coinsuranceApplied;
        this.planPaid = planPaid;
        this.memberResponsibility = memberResponsibility;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getDptCode() {
        return dptCode;
    }

    public void setDptCode(String dptCode) {
        this.dptCode = dptCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBilledAmount() {
        return billedAmount;
    }

    public void setBilledAmount(BigDecimal billedAmount) {
        this.billedAmount = billedAmount;
    }

    public BigDecimal getAllowedAmount() {
        return allowedAmount;
    }

    public void setAllowedAmount(BigDecimal allowedAmount) {
        this.allowedAmount = allowedAmount;
    }

    public BigDecimal getDeductibleApplied() {
        return deductibleApplied;
    }

    public void setDeductibleApplied(BigDecimal deductibleApplied) {
        this.deductibleApplied = deductibleApplied;
    }

    public BigDecimal getCopayApplied() {
        return copayApplied;
    }

    public void setCopayApplied(BigDecimal copayApplied) {
        this.copayApplied = copayApplied;
    }

    public BigDecimal getCoinsuranceApplied() {
        return coinsuranceApplied;
    }

    public void setCoinsuranceApplied(BigDecimal coinsuranceApplied) {
        this.coinsuranceApplied = coinsuranceApplied;
    }

    public BigDecimal getPlanPaid() {
        return planPaid;
    }

    public void setPlanPaid(BigDecimal planPaid) {
        this.planPaid = planPaid;
    }

    public BigDecimal getMemberResponsibility() {
        return memberResponsibility;
    }

    public void setMemberResponsibility(BigDecimal memberResponsibility) {
        this.memberResponsibility = memberResponsibility;
    }

    public Claim getClaim() {return claim;}
    public void setClaim(Claim claim) {this.claim = claim;}
}
