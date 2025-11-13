package com.liftly.liftly.repositories;

import com.liftly.liftly.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    @Query("SELECT w FROM Workout w WHERE YEAR(w.createdAt) = :year AND MONTH(w.createdAt) = :month AND DAY(w.createdAt) = :day" )
    List<Workout> findByYearAndMonth(@Param("year") int year, @Param("month") int month, @Param("day") int day);

}

