package com.liftly.liftly.models;

import com.liftly.liftly.dtos.CorporalWeightDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
@Table(name = "corporal_weights")
public class CorporalWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double weight;
    private String date;

    public CorporalWeightDTO toDto() {
        return CorporalWeightDTO.builder().id(Optional.ofNullable(id)).weight(weight).date(date).build();
    }
}
