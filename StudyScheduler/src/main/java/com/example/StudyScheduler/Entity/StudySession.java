package com.example.StudyScheduler.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "study_session")
public class StudySession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time",nullable = false)
    private LocalDateTime starTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Column(name = "planned_duration_minutes")
    private Integer planDurationMinutes;
    @Column(name = "actual_duration_minutes")
    private Integer actualDurationMinutes;
    @Column(name = "break_duration_minutes")
    private Integer breakDurationMinutes;

    @Enumerated(EnumType.STRING)
    private SessionType sessionType;

    @Enumerated(EnumType.STRING)
    private SessionStatus status;

    private String note;

    @Column(name = "focus_score")
    private Integer focusScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private StudyPlan studyPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @PrePersist
    protected void onCreate(){
        if (status == null) {
            status = SessionStatus.SCHEDULED;
        }
    }
}
enum SessionType {
    STUDY, BREAK, REVIEW
}
enum SessionStatus {
    SCHEDULED, IN_PROGRESS, COMPLETED, CANCLLED
}


