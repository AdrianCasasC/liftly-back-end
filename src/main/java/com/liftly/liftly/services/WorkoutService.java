package com.liftly.liftly.services;

import com.liftly.liftly.dtos.ExerciseDTO;
import com.liftly.liftly.dtos.WorkoutSetDTO;
import com.liftly.liftly.dtos.WorkoutDTO;
import com.liftly.liftly.models.Exercise;
import com.liftly.liftly.models.Workout;
import com.liftly.liftly.models.WorkoutSet;
import com.liftly.liftly.repositories.ExerciseRepository;
import com.liftly.liftly.repositories.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutService(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public List<Workout> getAllWorkouts(Optional<LocalDate> date) {
        if (date.isPresent()) {
            LocalDate dt = date.get();
            int year = dt.getYear();
            int month = dt.getMonthValue();
            int day = dt.getDayOfMonth();
            return workoutRepository.findByYearAndMonth(year, month, day);
        }

        // Si no viene fecha → devolver todos
        return workoutRepository.findAll();
    }

    public Workout createWorkout(LocalDate creationDate, WorkoutDTO dto) {
        Workout workout = new Workout();
        workout.setName(dto.getName());
        workout.setCreatedAt(creationDate);

        List<Exercise> exercises = new ArrayList<>();
        if (dto.getExercises() != null) {
            for (ExerciseDTO exDto : dto.getExercises()) {
                Exercise exercise = new Exercise();
                exercise.setName(exDto.getName());
                exercise.setWorkout(workout);

                List<WorkoutSet> sets = new ArrayList<>();
                if (exDto.getSets() != null) {
                    for (WorkoutSetDTO workoutSetDto : exDto.getSets()) {
                        WorkoutSet set = new WorkoutSet();
                        set.setOrderNumber(workoutSetDto.getOrderNumber());
                        set.setReps(workoutSetDto.getReps());
                        set.setWeight(workoutSetDto.getWeight());
                        set.setExercise(exercise);
                        sets.add(set);
                    }
                }
                exercise.setSets(sets);
                exercises.add(exercise);
            }
        }

        workout.setExercises(exercises);
        return workoutRepository.save(workout);
    }

    @Transactional
    public WorkoutDTO updateWorkout(Integer workoutId, WorkoutDTO dto) {
        Workout workout = workoutRepository.findById(workoutId.longValue())
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        workout.setName(dto.getName());

        // Remove old exercises
        workout.getExercises().clear();

        List<Exercise> exercises = dto.getExercises().stream().map(exDto -> {
            Exercise exercise = new Exercise();
            exercise.setName(exDto.getName());
            exercise.setWorkout(workout);

            List<WorkoutSet> sets = exDto.getSets().stream().map(workoutSetDto -> {
                WorkoutSet set = new WorkoutSet();
                set.setOrderNumber(workoutSetDto.getOrderNumber());
                set.setReps(workoutSetDto.getReps());
                set.setWeight(workoutSetDto.getWeight());
                set.setExercise(exercise);
                return set;
            }).collect(Collectors.toList());

            exercise.setSets(sets);
            return exercise;
        }).collect(Collectors.toList());

        workout.setExercises(exercises);

        workoutRepository.save(workout);

        return dto; // Optionally map back from entity to DTO
    }

    @Transactional
    public void deleteWorkout(Integer workoutId) {

        Workout workout = workoutRepository.findById(workoutId.longValue())
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        // 1. Remove child references (keeps Hibernate session consistent)
        if (workout.getExercises() != null) {
            for (Exercise ex : workout.getExercises()) {

                // Clear sets from each exercise
                if (ex.getSets() != null) {
                    ex.getSets().clear();
                }

                // Break reverse relationship so Hibernate doesn't complain
                ex.setWorkout(null);
            }

            // Clear exercises list from parent to avoid stale references
            workout.getExercises().clear();
        }

        // 2. Delete workout (cascades children due to CascadeType.ALL)
        workoutRepository.delete(workout);
    }
}
