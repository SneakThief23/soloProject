package com.SoloProject.solo.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "accumulator")
public class Accumulator {

    @Id
    @GeneratedValue
    private UUID id;
    private UUID enrollmentId;
    private String accumulatorType;       // DEDUCTIBLE or (out of pocket)OOP_MAX
    private String networkTier;           // IN_NETWORK/OUT_OF_NETWORK
    private BigDecimal limitAmount;     // e.g., 1500.00
    private BigDecimal usedAmount;      // e.g., 300.00

    public Accumulator() {}

    public Accumulator(UUID id, UUID enrollmentId, String accumulatorType, String networkTier, BigDecimal limitAmount, BigDecimal usedAmount) {
        this.id = id;
        this.enrollmentId = enrollmentId;
        this.accumulatorType = accumulatorType;
        this.networkTier = networkTier;
        this.limitAmount = limitAmount;
        this.usedAmount = usedAmount;
    }

    public UUID getId() {
        return id;
    }

    public UUID getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(UUID enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getAccumulatorType() {
        return accumulatorType;
    }

    public void setAccumulatorType(String accumulatorType) {
        this.accumulatorType = accumulatorType;
    }

    public String getNetworkTier() {
        return networkTier;
    }

    public void setNetworkTier(String networkTier) {
        this.networkTier = networkTier;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    public BigDecimal getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(BigDecimal usedAmount) {
        this.usedAmount = usedAmount;
    }
}