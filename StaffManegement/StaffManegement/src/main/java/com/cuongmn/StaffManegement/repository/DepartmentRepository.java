package com.cuongmn.StaffManegement.repository;

import com.cuongmn.StaffManegement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    boolean existsByname(String name);
}
