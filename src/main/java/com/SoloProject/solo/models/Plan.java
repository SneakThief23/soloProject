package com.SoloProject.solo.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;         // e.g., "Gold PPO"
    private String planType;       // PPO/HMO/...
    private String networkName;  // e.g., "Prime"
    private Integer planYear;    // e.g., 2025

    public Plan() {}

    public Plan(UUID id, String name, String planType, String networkName, Integer planYear) {
        this.id = id;
        this.name = name;
        this.planType = planType;
        this.networkName = networkName;
        this.planYear = planYear;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public Integer getPlanYear() {
        return planYear;
    }

    public void setPlanYear(Integer planYear) {
        this.planYear = planYear;
    }
}
