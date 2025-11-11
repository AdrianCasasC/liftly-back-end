package com.liftly.liftly.services;

import com.liftly.liftly.dtos.ExerciseDTO;
import com.liftly.liftly.models.Exercise;
import com.liftly.liftly.models.WorkoutSet;
import com.liftly.liftly.repositories.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Transactional
    public ExerciseDTO updateExercise(Integer id, ExerciseDTO dto) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        exercise.setName(dto.getName());

        if (exercise.getSets() == null) {
            List<WorkoutSet> newSets = dto.getSets().stream().map(setDto -> {
                WorkoutSet set = new WorkoutSet();
                set.setOrderNumber(setDto.getOrderNumber());
                set.setReps(setDto.getReps());
                set.setWeight(setDto.getWeight());
                set.setExercise(exercise);
                return set;
            }).collect(Collectors.toList());
            exercise.getSets().clear();
            exercise.getSets().addAll(newSets);
        }

        exerciseRepository.save(exercise);
        return dto;
    }
}
