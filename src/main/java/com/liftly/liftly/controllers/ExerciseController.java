package com.liftly.liftly.controllers;

import com.liftly.liftly.dtos.ExerciseDTO;
import com.liftly.liftly.services.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseDTO> updateExercise(@PathVariable Integer id, @RequestBody ExerciseDTO dto) {
        return ResponseEntity.ok(exerciseService.updateExercise(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Integer id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping()
    public ResponseEntity<ExerciseDTO> createExercise(@RequestParam Integer workoutId, @RequestBody ExerciseDTO dto) {
        return ResponseEntity.ok(exerciseService.createExercise(workoutId, dto));
    }
}
