package com.SoloProject.solo.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "accumulator")
public class Accumulator {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    private String accumulatorType;  // DEDUCTIBLE or OOP_MAX
    private String networkTier;      // IN_NETWORK/OUT_OF_NETWORK
    private BigDecimal limitAmount;
    private BigDecimal usedAmount;

    public Accumulator() {}

    public Accumulator(String accumulatorType, String networkTier, BigDecimal limitAmount, BigDecimal usedAmount) {
        this.accumulatorType = accumulatorType;
        this.networkTier = networkTier;
        this.limitAmount = limitAmount;
        this.usedAmount = usedAmount;
    }

    // getters/setters
    public UUID getId() { return id; }

    public Enrollment getEnrollment() { return enrollment; }
    public void setEnrollment(Enrollment enrollment) { this.enrollment = enrollment; }

    public String getAccumulatorType() { return accumulatorType; }
    public void setAccumulatorType(String accumulatorType) { this.accumulatorType = accumulatorType; }

    public String getNetworkTier() { return networkTier; }
    public void setNetworkTier(String networkTier) { this.networkTier = networkTier; }

    public BigDecimal getLimitAmount() { return limitAmount; }
    public void setLimitAmount(BigDecimal limitAmount) { this.limitAmount = limitAmount; }

    public BigDecimal getUsedAmount() { return usedAmount; }
    public void setUsedAmount(BigDecimal usedAmount) { this.usedAmount = usedAmount; }
}