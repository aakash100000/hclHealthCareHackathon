package org.hcl.healthcare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private ShiftInstance shiftInstance;
    @ManyToOne
    private User user;
    private LocalDate date;
    private boolean attendance;
//    private LocalDateTime created_at;
//    private LocalDateTime updated_at;
}
