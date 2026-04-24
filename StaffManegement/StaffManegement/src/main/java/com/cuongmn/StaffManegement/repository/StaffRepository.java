package com.cuongmn.StaffManegement.repository;

import com.cuongmn.StaffManegement.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff,Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Staff> findByUsername(String username);
}
