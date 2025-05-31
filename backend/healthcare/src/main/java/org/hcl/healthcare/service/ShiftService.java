package org.hcl.healthcare.service;

import lombok.extern.slf4j.Slf4j;
import org.hcl.healthcare.entity.ShiftInstance;
import org.hcl.healthcare.entity.ShiftType;
import org.hcl.healthcare.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ShiftService {
    private final ShiftRepository shiftRepository;
    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public ShiftInstance createShift(LocalDate date, String shiftType, int capacity) {
        log.info(shiftType.toString(), capacity, date);
        ShiftInstance shift = new ShiftInstance();
        shift.setDate(date);
        shift.setShiftType(ShiftType.valueOf(shiftType));
        shift.setCapacity(capacity);
        return shiftRepository.save(shift);
    }

    public List<ShiftInstance> getShiftsByDate(LocalDate date) {
        return shiftRepository.findByDate(date);
    }

    public ShiftInstance getById(Long id) {
        return shiftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShiftInstance not found with id: " + id));
    }

    public ShiftInstance reduceCapacity(ShiftInstance shift) {
        ShiftInstance shiftInstance = shiftRepository.findById(shift.getId())
                .orElseThrow(() -> new IllegalArgumentException("ShiftInstance with ID " + shift.getId() + " not found"));
        if (shiftInstance.getCapacity() <= 0) {
            throw new IllegalStateException("Capacity is already 0 for ShiftInstance ID " + shift.getId());
        }
        shiftInstance.setCapacity(shiftInstance.getCapacity() - 1);
        ShiftInstance save = shiftRepository.save(shiftInstance);
        return save;
    }

    public boolean checkforCapacity(ShiftInstance shift) {
        ShiftInstance shiftInstance = shiftRepository.findById(shift.getId())
                .orElseThrow(() -> new IllegalArgumentException("ShiftInstance with ID " + shift.getId() + " not found"));
        if (shiftInstance.getCapacity() == 0) {
            return false;
        }
        return true;
    }
}
