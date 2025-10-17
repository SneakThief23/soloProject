package com.SoloProject.solo.service;

import com.SoloProject.solo.models.Enrollment;
import com.SoloProject.solo.repos.EnrollmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EnrollmentService {

    private final EnrollmentRepo enrollmentRepo;

    public EnrollmentService(EnrollmentRepo enrollmentRepo) {
        this.enrollmentRepo = enrollmentRepo;
    }

    public List<Enrollment> getAllEnrollment() {
        return enrollmentRepo.findAll();
    }

    public List<Enrollment> getEnrollmentByMemberId(UUID memberId) {
        return enrollmentRepo.findByMemberId(memberId);
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepo.save(enrollment);
    }
    // Update existing Enrolment
    public Optional<Enrollment> updateEnrollment(UUID id, Enrollment newData) {
        return enrollmentRepo.findById(id).map(existing -> {
            if (newData.getMemberId () != null)existing.setMemberId(newData.getMemberId());
            if (newData.getPlanId () != null)existing.setPlanId(newData.getPlanId());
            if (newData.getCoverageEnd () != null)existing.setCoverageEnd(newData.getCoverageEnd());
            if (newData.getCoverageStart () != null)existing.setCoverageStart(newData.getCoverageStart());
            if (newData.getActive () != null)existing.setActive(newData.getActive());
            if (newData.getAccumulators () != null)existing.setAccumulators((newData.getAccumulators()));
            return enrollmentRepo.save(existing);
        });
    }
    public boolean deleteEnrollment(UUID id) {
        if (enrollmentRepo.existsById(id)) {
            enrollmentRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
