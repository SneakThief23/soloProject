package com.SoloProject.solo.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "claim_status_event")
public class ClaimStatusEvent {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claim_id", nullable = false)
    private Claim claim;

    private String claimStatus;
    private OffsetDateTime occurredAt;
    private String note;               // optional

    public ClaimStatusEvent() {}


    public ClaimStatusEvent(UUID id, Claim claim, String claimStatus, OffsetDateTime occurredAt, String note) {
        this.id = id;
        this.claim = claim;
        this.claimStatus = claimStatus;
        this.occurredAt = occurredAt;
        this.note = note;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Claim getClaim() { return claim; }
    public void setClaim(Claim claim) { this.claim = claim; }

    public String getClaimStatus() { return claimStatus; }
    public void setClaimStatus(String claimStatus) { this.claimStatus = claimStatus; }

    public OffsetDateTime getOccurredAt() { return occurredAt; }
    public void setOccurredAt(OffsetDateTime occurredAt) { this.occurredAt = occurredAt; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
