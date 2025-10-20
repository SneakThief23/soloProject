package com.SoloProject.solo.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Accumulator> accumulators = new ArrayList<>(); // Initialize here

    public Enrollment() {}

    public Enrollment(UUID memberId, UUID planId, LocalDate coverageStart, LocalDate coverageEnd, Boolean active, List<Accumulator> accumulators) {
        this.memberId = memberId;
        this.planId = planId;
        this.coverageStart = coverageStart;
        this.coverageEnd = coverageEnd;
        this.active = active;
        setAccumulators(accumulators);
    }

    // getters/setters
    public UUID getId() { return id; }

    public UUID getMemberId() { return memberId; }
    public void setMemberId(UUID memberId) { this.memberId = memberId; }

    public UUID getPlanId() { return planId; }
    public void setPlanId(UUID planId) { this.planId = planId; }

    public LocalDate getCoverageStart() { return coverageStart; }
    public void setCoverageStart(LocalDate coverageStart) { this.coverageStart = coverageStart; }

    public LocalDate getCoverageEnd() { return coverageEnd; }
    public void setCoverageEnd(LocalDate coverageEnd) { this.coverageEnd = coverageEnd; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public List<Accumulator> getAccumulators() { return accumulators; }

    public void setAccumulators(List<Accumulator> accumulators) {
        this.accumulators.clear();
        if (accumulators != null) {
            for (Accumulator acc : accumulators) {
                acc.setEnrollment(this); // important back-reference
                this.accumulators.add(acc);
            }
        }
    }
}