package com.liftly.liftly.controllers;

import com.liftly.liftly.dtos.WorkoutDTO;
import com.liftly.liftly.models.Workout;
import com.liftly.liftly.services.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public List<Workout> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    @PostMapping
    public Workout createWorkout(@RequestBody WorkoutDTO workoutDTO) {
        return workoutService.createWorkout(workoutDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutDTO> updateWorkout(@PathVariable Integer id, @RequestBody WorkoutDTO workoutDTO) {
        WorkoutDTO updated = workoutService.updateWorkout(id, workoutDTO);
        return ResponseEntity.ok(updated);
    }
}
