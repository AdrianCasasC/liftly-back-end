package com.liftly.liftly.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ClosestExerciseDTO {
    private Integer exerciseId;
    private String exerciseName;

    private Integer workoutId;
    private String workoutName;
    private LocalDate workoutDate;

    private List<WorkoutSetDTO> sets;
}
