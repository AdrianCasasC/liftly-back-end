package com.liftly.liftly.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkoutSetDTO {
    private int orderNumber;
    private int reps;
    private double weight;
}
