package com.liftly.liftly.controllers;

import com.liftly.liftly.dtos.WorkoutDTO;
import com.liftly.liftly.models.Workout;
import com.liftly.liftly.services.WorkoutService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public List<Workout> getAllWorkouts(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date)  {
        return workoutService.getAllWorkouts(Optional.ofNullable(date));
    }

    @PostMapping
    public Workout createWorkout(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate creationDate, @RequestBody WorkoutDTO workoutDTO) {
        return workoutService.createWorkout(creationDate, workoutDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutDTO> updateWorkout(@PathVariable Integer id, @RequestBody WorkoutDTO workoutDTO) {
        WorkoutDTO updated = workoutService.updateWorkout(id, workoutDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Integer id) {
        workoutService.deleteWorkout(id);
        return ResponseEntity.ok().build();
    }
}
