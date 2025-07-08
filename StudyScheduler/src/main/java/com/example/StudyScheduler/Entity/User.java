package com.example.StudyScheduler.Entity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails{
    
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastname;

    private StudyLevel studyLevel;

    private List<StudyPlan> studyPlans;

    private List<StudySession> studySessions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return email;
    }

    public boolean isAccountNonExpired(){
        return true;
    }

    public boolean isAccountNonLocked(){
        return true;
    }
    public boolean isCredentialsNonExpired(){
        return true;
    }
    public boolean isEnabled(){
        return true;
    }
}

enum StudyLevel {
    BEGINNER, INTERMEDIATE, ADVANCED
}
