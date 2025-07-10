package com.example.StudyScheduler.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.StudyScheduler.Entity.PlanStatus;
import com.example.StudyScheduler.Entity.StudyPlan;

@Repository
public interface StudyPlanRepository extends JpaRepository<StudyPlan, Long> {
    List<StudyPlan> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<StudyPlan> findByUserIdAndStatus(Long userId, PlanStatus status);

    @Query("SELECT sp FROM StudyPlan sp WHERE sp.user.id = :userId "+
            "AND sp.startDate <= :date AND sp.endDate >= :date")
    List<StudyPlan> findActiveStudyPlansForDate(@Param("userId") Long userId,
                                                @Param("date")LocalDate date);
}
