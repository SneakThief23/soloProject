package com.SoloProject.solo.controllers;

import com.SoloProject.solo.models.Accumulator;
import com.SoloProject.solo.service.AccumulatorService;
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
    public Optional<Accumulator> updateAccumulator(@PathVariable UUID id, @RequestBody Accumulator accumulator) {
        return accumulatorService.updateAccumulator(id, accumulator);
    }

    @DeleteMapping("/{id}")
    public String deleteAccumulator(@PathVariable UUID id) {
        return accumulatorService.deleteAccumulator(id) ? "Deleted" : "Not Found";
    }
}