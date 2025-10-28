package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Staff;
import com.example.University.Management.System.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public void addStaff(Staff staff) {
        staffRepository.save(staff);
    }

    public void removeStaff(String staffId) {
        Staff staff = staffRepository.findById(staffId);
        if (staff != null) {
            staffRepository.delete(staff);
        }
    }

    public Staff getStaffById(String id) {
        return staffRepository.findById(id);
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }
}
