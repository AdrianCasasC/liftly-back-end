package com.liftly.liftly.services;

import com.liftly.liftly.dtos.ExerciseDTO;
import com.liftly.liftly.dtos.SetDTO;
import com.liftly.liftly.dtos.WorkoutDTO;
import com.liftly.liftly.models.Exercise;
import com.liftly.liftly.models.Workout;
import com.liftly.liftly.models.WorkoutSet;
import com.liftly.liftly.repositories.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    public Workout createWorkout(WorkoutDTO dto) {
        Workout workout = new Workout();
        workout.setName(dto.getName());

        List<Exercise> exercises = new ArrayList<>();
        if (dto.getExercises() != null) {
            for (ExerciseDTO exDto : dto.getExercises()) {
                Exercise exercise = new Exercise();
                exercise.setName(exDto.getName());
                exercise.setWorkout(workout);

                List<WorkoutSet> sets = new ArrayList<>();
                if (exDto.getSets() != null) {
                    for (SetDTO setDto : exDto.getSets()) {
                        WorkoutSet set = new WorkoutSet();
                        set.setOrderNumber(setDto.getOrder());
                        set.setReps(setDto.getReps());
                        set.setWeight(setDto.getWeight());
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
}
