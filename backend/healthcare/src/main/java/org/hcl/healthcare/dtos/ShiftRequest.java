package org.hcl.healthcare.dtos;

import lombok.Data;
import org.hcl.healthcare.entity.ShiftType;

import java.time.LocalDate;

@Data
public class ShiftRequest {
    private ShiftType shiftType;
   // private LocalDate date;
    private int capacity;
}
