package com.liftly.liftly.services;

import com.liftly.liftly.dtos.ExerciseDTO;
import com.liftly.liftly.models.Exercise;
import com.liftly.liftly.models.Workout;
import com.liftly.liftly.models.WorkoutSet;
import com.liftly.liftly.repositories.ExerciseRepository;
import com.liftly.liftly.repositories.WorkoutRepository;
import com.liftly.liftly.repositories.WorkoutSetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final WorkoutSetService setService;

    public ExerciseService(ExerciseRepository exerciseRepository, WorkoutRepository workoutRepositor, WorkoutSetService setService) {
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepositor;
        this.setService = setService;
    }

    @Transactional
    public ExerciseDTO updateExercise(Integer id, ExerciseDTO dto) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        exercise.setName(dto.getName());

        if (exercise.getSets() != null) {
            List<WorkoutSet> newSets = dto.getSets().stream().map(setDto -> {
                WorkoutSet set = new WorkoutSet();
                set.setOrderNumber(setDto.getOrderNumber());
                set.setReps(setDto.getReps());
                set.setWeight(setDto.getWeight());
                set.setExercise(exercise);
                return set;
            }).toList();
            exercise.getSets().clear();
            exercise.getSets().addAll(newSets);
        }

        exerciseRepository.save(exercise);
        return dto;
    }

    @Transactional
    public ExerciseDTO createExercise(Integer workoutId, ExerciseDTO dto) {
        Workout workout = workoutRepository.findById(workoutId.longValue())
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        Exercise exerciseEntity = toExerciseEntity(dto);
        exerciseEntity.setWorkout(workout);

        exerciseRepository.save(exerciseEntity);
        return dto;
    }

    private Exercise toExerciseEntity(ExerciseDTO dto) {
        Exercise exercise = new Exercise();
        exercise.setName(dto.getName());
        exercise.setSets(setService.toEntitySetFromList(dto.getSets()));
        List<WorkoutSet> sets = exercise.getSets();
        sets.forEach(set -> set.setExercise(exercise));
        return exercise;
    };

    public void deleteExercise(Integer id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        Workout workout = exercise.getWorkout();
        // Prevent stale references inside Hibernate session
        if (workout != null && workout.getExercises() != null) {
            workout.getExercises().remove(exercise);
        }
        exerciseRepository.delete(exercise);
    }
}
