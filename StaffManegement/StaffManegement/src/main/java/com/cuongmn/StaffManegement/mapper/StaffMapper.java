package com.cuongmn.StaffManegement.mapper;

import com.cuongmn.StaffManegement.dto.request.StaffCreateRequest;
import com.cuongmn.StaffManegement.dto.request.StaffUpdateRequest;
import com.cuongmn.StaffManegement.dto.response.StaffResponse;
import com.cuongmn.StaffManegement.entity.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    /*
     * department được set trong Service, vì request chỉ có departmentId.
     * password cũng xử lý trong Service vì cần BCrypt.
     */
    @Mapping(target = "department", ignore = true)
    Staff toStaff(StaffCreateRequest request);

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.name", target = "departmentName")
    StaffResponse toStaffResponse(Staff staff);

    @Mapping(target = "department", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "username", ignore = true)
    void updateStaff(@MappingTarget Staff staff, StaffUpdateRequest request);
}