package com.liftly.liftly.controllers;

import com.liftly.liftly.dtos.CorporalWeightDTO;
import com.liftly.liftly.dtos.ExerciseCollectionDTO;
import com.liftly.liftly.services.CorporalWeightService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/corporal-weight")
public class CorporalWeightController {
    private final CorporalWeightService weightService;

    public CorporalWeightController(CorporalWeightService weightService) {
        this.weightService = weightService;
    }

    @GetMapping
    public ResponseEntity<List<CorporalWeightDTO>> getAllWeights() {
        return ResponseEntity.ok(weightService.getAllWeights());
    }

    @PostMapping
    public ResponseEntity<CorporalWeightDTO> saveWeight(CorporalWeightDTO dto) {
        return ResponseEntity.ok(weightService.saveCorporalWeight(dto));
    }
}
