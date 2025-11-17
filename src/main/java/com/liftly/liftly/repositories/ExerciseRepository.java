package com.liftly.liftly.repositories;

import com.liftly.liftly.models.Exercise;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    @Query("""
    SELECT e
    FROM Exercise e
    JOIN e.workout w
    WHERE e.name = :name
      AND DATE(w.createdAt) < :date
    ORDER BY ABS(DATEDIFF(w.createdAt, :date)) ASC
    """)
    List<Exercise> findClosestByName(@Param("name") String name, @Param("date") LocalDate date, Pageable pageable);
}
