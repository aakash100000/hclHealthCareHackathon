package org.hcl.healthcare.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hcl.healthcare.entity.Schedule;
import org.hcl.healthcare.entity.ShiftInstance;
import org.hcl.healthcare.entity.User;
import org.hcl.healthcare.repository.ScheduleRepository;
import org.hcl.healthcare.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ShiftService shiftService;
    private final UserService userService;
    //private final ScheduleService scheduleService;


    @Transactional
    public Schedule assignShift(User staff, ShiftInstance shift, LocalDate date) {

       // if(shiftService.checkforCapacity(shift) && checkForSlot(staff,date)){
            Schedule schedule = new Schedule();
            schedule.setUser(staff);
            schedule.setShiftInstance(shift);
            schedule.setDate(date);
            Schedule saved = scheduleRepository.save(schedule);

            // shiftService.reduceCapacity(shift);

            return saved;
       // }else {
       //     throw new RuntimeException("Shift capacity exceeded");
       // }


    }

    private boolean checkForSlot(User staff, LocalDate date) {
        Optional<List<Schedule>> byDateAndUser = scheduleRepository.findByDateAndUser(date, staff);
        if(byDateAndUser.isPresent()){
            return false;
        }
        return true;
    }

//    public List<Schedule> getSchedulesByDate(LocalDate date) {
//        List<Schedule> byDate = scheduleRepository.findByDate(date);
//        return byDate;
//    }
}
