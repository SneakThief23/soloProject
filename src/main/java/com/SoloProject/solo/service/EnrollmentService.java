package com.SoloProject.solo.service;

import com.SoloProject.solo.models.Enrollment;
import com.SoloProject.solo.repos.EnrollmentRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EnrollmentService {

    private final EnrollmentRepo enrollmentRepo;

    public EnrollmentService(EnrollmentRepo enrollmentRepo) {
        this.enrollmentRepo = enrollmentRepo;
    }
    // Update existing Enrolment
    public Optional<Enrollment> updateEnrollment(UUID id, Enrollment newData) {
        return enrollmentRepo.findById(id).map(existing -> {
            existing.setMemberId(newData.getMemberId());
            existing.setPlanId(newData.getPlanId());
            existing.setCoverageEnd(newData.getCoverageEnd());
            existing.setCoverageStart(newData.getCoverageStart());
            existing.setActive(newData.getActive());
            existing.setAccumulators((newData.getAccumulators()));
            return enrollmentRepo.save(existing);
        });
    }
}
