package org.hcl.healthcare.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class ShiftInstance {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private ShiftType shiftType;

    private LocalDate date;
    private int capacity;

}
