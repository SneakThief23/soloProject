package com.SoloProject.solo.controllers;

import com.SoloProject.solo.models.Accumulator;
import com.SoloProject.solo.service.AccumulatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/accumulator")
public class AccumulatorController {

    private final AccumulatorService accumulatorService;

    public AccumulatorController(AccumulatorService accumulatorService) {
        this.accumulatorService = accumulatorService;
    }

    @GetMapping
    public List<Accumulator> getAllAccumulators() {
        return accumulatorService.getAllAccumulators();
    }

    @PostMapping
    public Accumulator createAccumulator(@RequestBody Accumulator accumulator) {
        return accumulatorService.createAccumulator(accumulator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Accumulator> updateAccumulator(@PathVariable UUID id, @RequestBody Accumulator data) {
        Optional<Accumulator> updated = accumulatorService.updateAccumulator(id, data);
        return updated
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccumulator(@PathVariable UUID id) {
        boolean deleted = accumulatorService.deleteAccumulator(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}