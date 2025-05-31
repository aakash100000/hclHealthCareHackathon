package org.hcl.healthcare.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hcl.healthcare.dtos.LoginRequest;
import org.hcl.healthcare.dtos.ScheduleRequest;
import org.hcl.healthcare.dtos.ShiftRequest;
import org.hcl.healthcare.entity.*;
import org.hcl.healthcare.repository.ShiftRepository;
import org.hcl.healthcare.repository.UserRepository;
import org.hcl.healthcare.service.ScheduleService;
import org.hcl.healthcare.service.ShiftService;
import org.hcl.healthcare.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class StaffController {

    private final UserService userService;
    private final ShiftService shiftService;
    private final ScheduleService scheduleService;

    //getShiftByDate api gets called where he sees all shiftInstances for that date,now he can choose to create shift for that particular date using createShift api. Admin can then choose a date,staff(for which call getAllStaffs spi),shiftInstance(for which we call getShiftByDate api ) and call assignShiftInstanceToStaff to create a schedule. entity we have are Schedule(for all assignments),ShiftIntance(shifs for each date with capacity),User(with usertype as staff and non staff)
@PostMapping("/login")
public ResponseEntity<User> login(@RequestBody LoginRequest request) {
    User user = userService.login(request.getUsername(), request.getPassword());
//    if (user != null ) {
//        return ResponseEntity.ok(user);
//    }
    if (user != null && user.getRole().equals(Role.ADMIN)) {
        return ResponseEntity.ok(user);
    }




    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(user);
}

    @PostMapping("/shift")
    public ResponseEntity<ShiftInstance> createShift(@RequestParam ShiftType shiftType,@RequestParam Integer capacity, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
    log.info(shiftType.toString(), capacity, date);
    return ResponseEntity.ok(
                shiftService.createShift(date, String.valueOf(shiftType), capacity)
        );

    }

    @GetMapping("/shift")
    public ResponseEntity<List<ShiftInstance>> getShiftByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return ResponseEntity.ok(shiftService.getShiftsByDate(date));
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignShiftToStaff(@RequestBody ScheduleRequest request,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        User staff = userService.getById(request.getStaffId());
        ShiftInstance shift = shiftService.getById(request.getShiftInstanceId());
        return ResponseEntity.ok(scheduleService.assignShift(staff, shift, date));
    }

    @GetMapping("/staffs")
    public ResponseEntity<List<User>> getAllStaffs() {
        List<User> allStaffs = userService.getAllStaffs();
        allStaffs.forEach(s->System.out.println(s));
        return ResponseEntity.ok(allStaffs);
    }



}
