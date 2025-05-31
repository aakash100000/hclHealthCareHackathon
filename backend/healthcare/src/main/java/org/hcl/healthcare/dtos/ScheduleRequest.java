package org.hcl.healthcare.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleRequest {
    private Long staffId;
    private Long shiftInstanceId;
  //  private LocalDate date;
}
