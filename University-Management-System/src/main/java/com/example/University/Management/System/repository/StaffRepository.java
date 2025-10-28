package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Staff;
import org.springframework.stereotype.Repository;

@Repository
public class StaffRepository extends InMemoryRepository<Staff> {

    @Override
    protected String getId(Staff staff) {
        return staff.getStaffID();
    }

    @Override
    protected void setId(Staff staff, String id) {
        staff.setStaffID(id);
    }
}
