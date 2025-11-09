package com.liftly.liftly.dtos;


import lombok.Data;

import java.util.List;

@Data
public class ExerciseDTO {
    private String name;
    private List<SetDTO> sets;
}
