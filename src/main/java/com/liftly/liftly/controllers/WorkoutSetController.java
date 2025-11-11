package com.liftly.liftly.controllers;

import com.liftly.liftly.dtos.WorkoutSetDTO;
import com.liftly.liftly.services.WorkoutSetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sets")
public class WorkoutSetController {

    private final WorkoutSetService setService;

    public WorkoutSetController(WorkoutSetService setService) {
        this.setService = setService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutSetDTO> updateSet(@PathVariable Integer id, @RequestBody WorkoutSetDTO dto) {
        return ResponseEntity.ok(setService.updateWorkoutSet(id, dto));
    }
}

