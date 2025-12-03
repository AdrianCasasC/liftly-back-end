package com.liftly.liftly.dtos;

import com.liftly.liftly.models.CorporalWeight;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public class CorporalWeightDTO {
    private Optional<Integer> id;
    private double weight;
    private String date;

    public CorporalWeight toEntity() {
        Integer dtoId = id.orElse(null);
        return CorporalWeight.builder().id(dtoId).weight(weight).date(date).build();
    }
}
