package com.liftly.liftly.controllers;

import com.liftly.liftly.dtos.ClosestExerciseDTO;
import com.liftly.liftly.dtos.ExerciseDTO;
import com.liftly.liftly.dtos.ExerciseCollectionDTO;
import com.liftly.liftly.services.ExerciseService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @PostMapping("/list")
    public ResponseEntity<ExerciseCollectionDTO> addExerciseToCollection(@RequestBody ExerciseCollectionDTO dto) {
        return ResponseEntity.ok(exerciseService.addExerciseToCollection(dto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ExerciseCollectionDTO>> getExerciseCollection() {
        return ResponseEntity.ok(exerciseService.getExerciseCollection());
    }

    @GetMapping("/closest")
    public ResponseEntity<List<ClosestExerciseDTO>> getClosestExercises(@RequestParam("name") String name, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(exerciseService.getClosestExercises(name, date));
    }
}
