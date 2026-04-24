package com.cuongmn.StaffManegement.mapper;

import com.cuongmn.StaffManegement.dto.request.DepartmentCreateRequest;
import com.cuongmn.StaffManegement.dto.request.DepartmentUpdateRequest;
import com.cuongmn.StaffManegement.dto.response.DepartmentResponse;
import com.cuongmn.StaffManegement.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    @Mapping(target = "description", source = "description")
    Department toDepartment(DepartmentCreateRequest request);

    DepartmentResponse toDepartmentResponse(Department department);

    void updateDepartment(@MappingTarget Department department, DepartmentUpdateRequest request);
}