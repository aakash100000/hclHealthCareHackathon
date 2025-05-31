package org.hcl.healthcare.repository;

import org.hcl.healthcare.entity.ShiftInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<ShiftInstance,Long> {
    List<ShiftInstance> findByDate(LocalDate date);
}
