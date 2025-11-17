package com.liftly.liftly.dtos;


import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@Builder
public class ExerciseDTO {
    private Optional<Integer> id;
    private String name;
    private List<WorkoutSetDTO> sets;
}
