package com.SoloProject.solo.service;

import com.SoloProject.solo.models.Document;
import com.SoloProject.solo.repos.DocumentRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepo documentRepo;

    public DocumentService(DocumentRepo documentRepo) {
        this.documentRepo = documentRepo;
    }

    public List<Document> getAllDocument() {
        return documentRepo.findAll();
    }

    public List<Document> getDocumentByMemberId(UUID memberId) {
        return documentRepo.findByMemberId(memberId);
    }

    public Document createDocument(Document document){
        return documentRepo.save(document);
    }

    // Update existing Document
    public Optional<Document> updateDocument(UUID id, Document newData) {
        return documentRepo.findById(id).map(existing -> {
            if (newData.getMemberId () != null)existing.setId(newData.getMemberId());
            if (newData.getFileName () != null)existing.setFileName(newData.getFileName());
            if (newData.getMimeType () != null)existing.setMimeType(newData.getMimeType());
            if (newData.getMemberId () != null)existing.setMemberId(newData.getMemberId());
            if (newData.getClaimId () != null)existing.setClaimId(newData.getClaimId());
            if (newData.getUploadedAt () != null)existing.setUploadedAt((newData.getUploadedAt()));
            return documentRepo.save(existing);
        });
    }

    public boolean deleteDocument(UUID id) {
        if(documentRepo.existsById(id)) {
            documentRepo.deleteById(id);
            return true;
        }
        return false;
    }
}

