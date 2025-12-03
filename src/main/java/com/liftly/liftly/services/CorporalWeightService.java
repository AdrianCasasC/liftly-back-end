package com.liftly.liftly.services;

import com.liftly.liftly.dtos.CorporalWeightDTO;
import com.liftly.liftly.models.CorporalWeight;
import com.liftly.liftly.repositories.CorporalWeightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorporalWeightService {

    private final CorporalWeightRepository repo;

    public CorporalWeightService(CorporalWeightRepository repo) {
        this.repo = repo;
    }

    public List<CorporalWeightDTO> getAllWeights() {
        List<CorporalWeight> weights = repo.findAll();
        return weights.stream().map(CorporalWeight::toDto).toList();
    }

    public CorporalWeightDTO saveCorporalWeight(CorporalWeightDTO dto) {
        CorporalWeight savedEntity = repo.save(dto.toEntity());
        return savedEntity.toDto();
    }

}
