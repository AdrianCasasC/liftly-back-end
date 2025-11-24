package com.liftly.liftly.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExerciseCollectionDTO {
    private String label;
    private String value;
    private String muscle;
}
