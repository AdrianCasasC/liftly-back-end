package com.liftly.liftly.services;

import com.liftly.liftly.dtos.WorkoutSetDTO;
import com.liftly.liftly.models.WorkoutSet;
import com.liftly.liftly.repositories.ExerciseRepository;
import com.liftly.liftly.repositories.WorkoutSetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WorkoutSetService {
    private final WorkoutSetRepository setRepository;

    public WorkoutSetService(WorkoutSetRepository setRepository) {
        this.setRepository = setRepository;
    }
    public WorkoutSetDTO updateWorkoutSet(Integer id, WorkoutSetDTO dto) {
        WorkoutSet set = setRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Set not found"));

        set.setOrderNumber(dto.getOrderNumber());
        set.setReps(dto.getReps());
        set.setWeight(dto.getWeight());

        setRepository.save(set);

        return dto;
    }
}
