package com.liftly.liftly.dtos;


import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class ExerciseDTO {
    private Optional<Integer> id;
    private String name;
    private List<WorkoutSetDTO> sets;
}
