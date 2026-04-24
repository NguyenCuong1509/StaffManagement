package com.cuongmn.StaffManegement.service;

import com.cuongmn.StaffManegement.dto.request.StaffCreateRequest;
import com.cuongmn.StaffManegement.dto.request.StaffUpdateRequest;
import com.cuongmn.StaffManegement.dto.response.StaffResponse;
import com.cuongmn.StaffManegement.entity.Department;
import com.cuongmn.StaffManegement.entity.Staff;
import com.cuongmn.StaffManegement.exception.AppException;
import com.cuongmn.StaffManegement.exception.ErrorCode;
import com.cuongmn.StaffManegement.mapper.StaffMapper;
import com.cuongmn.StaffManegement.repository.DepartmentRepository;
import com.cuongmn.StaffManegement.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final DepartmentRepository departmentRepository;
    private final StaffMapper staffMapper;
    private final PasswordEncoder passwordEncoder;

    public StaffResponse createStaff(StaffCreateRequest request) {
        if (staffRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.STAFF_EXISTED);
        }

        if (staffRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.STAFF_EXISTED);
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));

        Staff staff = staffMapper.toStaff(request);

        // Không lưu password gốc
        staff.setPassword(passwordEncoder.encode(request.getPassword()));

        // Gắn foreign key thông qua object Department
        staff.setDepartment(department);

        staff = staffRepository.save(staff);

        return staffMapper.toStaffResponse(staff);
    }

    public List<StaffResponse> getStaffList() {
        return staffRepository.findAll()
                .stream()
                .map(staffMapper::toStaffResponse)
                .toList();
    }

    public StaffResponse getStaff(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STAFF_NOT_FOUND));

        return staffMapper.toStaffResponse(staff);
    }

    public StaffResponse updateStaff(Long id, StaffUpdateRequest request) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STAFF_NOT_FOUND));

        staffMapper.updateStaff(staff, request);

        if (request.getDepartmentId() != null) {
            Department department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));

            staff.setDepartment(department);
        }

        staff = staffRepository.save(staff);

        return staffMapper.toStaffResponse(staff);
    }

    public void deleteStaff(Long id) {
        if (!staffRepository.existsById(id)) {
            throw new AppException(ErrorCode.STAFF_NOT_FOUND);
        }

        staffRepository.deleteById(id);
    }
}