package com.SoloProject.solo.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;
import java.time.OffsetDateTime;

@Entity
@Table(name = "Document")
public class Document {

    @Id
    @GeneratedValue
    private UUID id;
    private String fileName;
    private String mimeType;     // "application/pdf"
    private String storagePath;  // e.g., "/static/eobs/C-10421.pdf"
    private UUID memberId;
    private UUID claimId;        // for EOB, nullable otherwise
    private OffsetDateTime uploadedAt;

    public Document() {}

    public Document(UUID id, String fileName, String mimeType, String storagePath, UUID memberId, UUID claimId, OffsetDateTime uploadedAt) {
        this.id = id;
        this.fileName = fileName;
        this.mimeType = mimeType;
        this.storagePath = storagePath;
        this.memberId = memberId;
        this.claimId = claimId;
        this.uploadedAt = uploadedAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }

    public UUID getClaimId() {
        return claimId;
    }

    public void setClaimId(UUID claimId) {
        this.claimId = claimId;
    }

    public OffsetDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(OffsetDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
