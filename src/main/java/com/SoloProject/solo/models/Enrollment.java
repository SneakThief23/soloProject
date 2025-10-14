package com.SoloProject.solo.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue
    private UUID id;
    private UUID memberId;
    private UUID planId;
    private LocalDate coverageStart;
    private LocalDate coverageEnd;
    private Boolean active;
    private List<Accumulator> accumulators;

    public Enrollment() {}

    public Enrollment(UUID id, UUID memberId, UUID planId, LocalDate coverageStart, LocalDate coverageEnd, Boolean active, List<Accumulator> accumulators) {
        this.id = id;
        this.memberId = memberId;
        this.planId = planId;
        this.coverageStart = coverageStart;
        this.coverageEnd = coverageEnd;
        this.active = active;
        this.accumulators = accumulators;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public UUID getPlanId() {
        return planId;
    }

    public void setPlanId(UUID planId) {
        this.planId = planId;
    }

    public LocalDate getCoverageStart() {
        return coverageStart;
    }

    public void setCoverageStart(LocalDate coverageStart) {
        this.coverageStart = coverageStart;
    }

    public LocalDate getCoverageEnd() {
        return coverageEnd;
    }

    public void setCoverageEnd(LocalDate coverageEnd) {
        this.coverageEnd = coverageEnd;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Accumulator> getAccumulators() {
        return accumulators;
    }

    public void setAccumulators(List<Accumulator> accumulators) {
        this.accumulators = accumulators;
    }
}
