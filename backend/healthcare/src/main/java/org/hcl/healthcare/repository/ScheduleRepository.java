package org.hcl.healthcare.repository;

import org.hcl.healthcare.entity.Schedule;
import org.hcl.healthcare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<List<Schedule>> findByDateAndUser(LocalDate date, User user);
}
