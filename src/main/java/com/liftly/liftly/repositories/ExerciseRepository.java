package com.liftly.liftly.repositories;

import com.liftly.liftly.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {}
