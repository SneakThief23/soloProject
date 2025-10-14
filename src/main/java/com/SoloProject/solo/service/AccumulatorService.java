package com.SoloProject.solo.service;

import com.SoloProject.solo.models.Accumulator;
import com.SoloProject.solo.repos.AccumulatorRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class AccumulatorService {

    private final AccumulatorRepo accumulatorRepo;

    public AccumulatorService(AccumulatorRepo accumulatorRepo) {
        this.accumulatorRepo = accumulatorRepo;
    }

    // Update existing Accumulator
public Optional<Accumulator> updateAccumulator(UUID id, Accumulator newData){
        return accumulatorRepo.findById(id).map(existing -> {
            existing.setAccumulatorType(newData.getAccumulatorType());
            existing.setNetworkTier(newData.getNetworkTier());
            existing.setLimitAmount(newData.getLimitAmount());
            existing.setUsedAmount(newData.getUsedAmount());
            return accumulatorRepo.save(existing);
        });
    }

    //Create a new accumulator
    public Accumulator createAccumulator(Accumulator accumulator) {
        return accumulatorRepo.save(accumulator);
    }

    public List<Accumulator> getAllAccumulator() {
        return accumulatorRepo.findAll();
    }
    //Delete an accumulator
    public boolean deleteAccumulator(UUID id) {
        if (accumulatorRepo.existsById(id)) {
            accumulatorRepo.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Accumulator> getAllAccumulators() {
        return accumulatorRepo.findAll();
    }
}
