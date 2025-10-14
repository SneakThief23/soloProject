package com.SoloProject.solo.service;

import com.SoloProject.solo.models.Document;
import com.SoloProject.solo.repos.DocumentRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentService {
    private final DocumentRepo documentRepo;

    public DocumentService(DocumentRepo documentRepo) {
        this.documentRepo = documentRepo;
    }
    // Update existing Document
    public Optional<Document> updateDocument(UUID id, Document newData) {
        return documentRepo.findById(id).map(existing -> {
            existing.setId(newData.getMemberId());
            existing.setFileName(newData.getFileName());
            existing.setMimeType(newData.getMimeType());
            existing.setMemberId(newData.getMemberId());
            existing.setClaimId(newData.getClaimId());
            existing.setUploadedAt((newData.getUploadedAt()));
            return documentRepo.save(existing);
        });
    }
}

