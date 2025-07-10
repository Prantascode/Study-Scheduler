package com.example.StudyScheduler.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "study_plan")
public class StudyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String discription;

    @Column(name = "start_date",nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date",nullable = false)
    private LocalDate endDate;
    
    @Column(name = "daily_study_hour")
    private Integer dailyStudyHour;

    @Enumerated(EnumType.STRING)
    private PlanStatus status;

    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "studyPlan",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Subject> subjects;

    @OneToMany(mappedBy = "studyPlan",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<StudySession> studySessions;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
        if (status == null) {
            status = PlanStatus.ACTIVE;
        }
    }
    protected void onUpdate(){
        updateAt = LocalDateTime.now();
    }
}

