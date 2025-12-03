package com.liftly.liftly.repositories;

import com.liftly.liftly.models.CorporalWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporalWeightRepository extends JpaRepository<CorporalWeight, Long> {

}
