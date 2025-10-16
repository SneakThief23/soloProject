package com.SoloProject.solo.controllers;

import com.SoloProject.solo.models.Enrollment;
import com.SoloProject.solo.service.EnrollmentService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public List<Enrollment> getAllEnrollment() {
        return enrollmentService.getAllEnrollment();
    }

    @GetMapping("/user/{id}")
    public List<Enrollment> getEnrollmentByUser(@PathVariable("id")UUID id){
        return enrollmentService.getEnrollmentByMemberId(id);
    }

    @PostMapping
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.createEnrollment(enrollment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable UUID id, @RequestBody Enrollment enrollment) {
        Optional<Enrollment> updated = enrollmentService.updateEnrollment(id, enrollment);
        return updated
                .map(acc -> ResponseEntity.ok(acc))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable UUID id) {
        boolean deleted = enrollmentService.deleteEnrollment(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
