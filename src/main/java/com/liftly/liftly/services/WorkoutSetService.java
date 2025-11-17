package com.liftly.liftly.services;

import com.liftly.liftly.dtos.WorkoutSetDTO;
import com.liftly.liftly.models.WorkoutSet;
import com.liftly.liftly.repositories.ExerciseRepository;
import com.liftly.liftly.repositories.WorkoutSetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public WorkoutSet toEntitySet(WorkoutSetDTO dto) {
        WorkoutSet set = new WorkoutSet();
        set.setOrderNumber(dto.getOrderNumber());
        set.setReps(dto.getReps());
        set.setWeight(dto.getWeight());
        return set;
    }

    public List<WorkoutSet> toEntitySetFromList(List<WorkoutSetDTO> dtos) {
         return dtos.stream().map(this::toEntitySet).toList();
    }

    public WorkoutSetDTO toDtoSet(WorkoutSet entity) {
        return WorkoutSetDTO.builder()
                .orderNumber(entity.getOrderNumber())
                .reps(entity.getReps())
                .weight(entity.getWeight())
                .build();
    }

    public List<WorkoutSetDTO> toDtoSetFromList(List<WorkoutSet> sets) {
        return sets.stream().map(this::toDtoSet).toList();
    }
}
