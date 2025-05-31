package org.hcl.healthcare.service;

import lombok.RequiredArgsConstructor;
import org.hcl.healthcare.entity.Schedule;
import org.hcl.healthcare.entity.ShiftInstance;
import org.hcl.healthcare.entity.User;
import org.hcl.healthcare.entity.UserType;
import org.hcl.healthcare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    //private final ScheduleService scheduleService;


    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public List<User> getAllStaffs() {
        List<User> allByType = userRepository.findAllByType(UserType.STAFF);
        allByType.forEach(s->System.out.println(s));
        return allByType;
    }


    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }


}
