package com.liftly.liftly.repositories;

import com.liftly.liftly.models.Exercise;
import com.liftly.liftly.models.ListExercise;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseListRepository extends JpaRepository<ListExercise, Integer> { }
