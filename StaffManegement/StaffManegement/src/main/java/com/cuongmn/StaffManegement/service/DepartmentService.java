package com.cuongmn.StaffManegement.service;

import com.cuongmn.StaffManegement.dto.request.DepartmentCreateRequest;
import com.cuongmn.StaffManegement.dto.request.DepartmentUpdateRequest;
import com.cuongmn.StaffManegement.dto.response.DepartmentResponse;
import com.cuongmn.StaffManegement.entity.Department;
import com.cuongmn.StaffManegement.exception.AppException;
import com.cuongmn.StaffManegement.exception.ErrorCode;
import com.cuongmn.StaffManegement.mapper.DepartmentMapper;
import com.cuongmn.StaffManegement.repository.DepartmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    public DepartmentResponse createDepartment(DepartmentCreateRequest request) {
        if (departmentRepository.existsByname(request.getName())) {
            throw new AppException(ErrorCode.DEPARTMENT_EXISTED);
        }
        Department department = departmentMapper.toDepartment(request);
        department = departmentRepository.save(department);
        return departmentMapper.toDepartmentResponse(department);
    }


    public List<DepartmentResponse> getDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toDepartmentResponse)
                .toList();
    }

    public DepartmentResponse getDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));

        return departmentMapper.toDepartmentResponse(department);
    }

    public DepartmentResponse updateDepartment(Long id, DepartmentUpdateRequest request) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));

        departmentMapper.updateDepartment(department, request);
        department = departmentRepository.save(department);

        return departmentMapper.toDepartmentResponse(department);
    }

    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new AppException(ErrorCode.DEPARTMENT_NOT_FOUND);
        }

        departmentRepository.deleteById(id);
    }
}
