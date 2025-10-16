package com.SoloProject.solo.repos;

import com.SoloProject.solo.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;

public interface DocumentRepo extends JpaRepository<Document, UUID> {
    List<Document> findByMemberId(UUID memberId);
}