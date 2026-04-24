package com.cuongmn.StaffManegement.mapper;

import com.cuongmn.StaffManegement.dto.request.StaffCreateRequest;
import com.cuongmn.StaffManegement.dto.request.StaffUpdateRequest;
import com.cuongmn.StaffManegement.dto.response.StaffResponse;
import com.cuongmn.StaffManegement.entity.Department;
import com.cuongmn.StaffManegement.entity.Staff;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-24T16:31:42+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.10 (Oracle Corporation)"
)
@Component
public class StaffMapperImpl implements StaffMapper {

    @Override
    public Staff toStaff(StaffCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Staff.StaffBuilder staff = Staff.builder();

        staff.username( request.getUsername() );
        staff.password( request.getPassword() );
        staff.fullName( request.getFullName() );
        staff.email( request.getEmail() );
        staff.role( request.getRole() );

        return staff.build();
    }

    @Override
    public StaffResponse toStaffResponse(Staff staff) {
        if ( staff == null ) {
            return null;
        }

        StaffResponse.StaffResponseBuilder staffResponse = StaffResponse.builder();

        staffResponse.departmentId( staffDepartmentId( staff ) );
        staffResponse.departmentName( staffDepartmentName( staff ) );
        staffResponse.id( staff.getId() );
        staffResponse.username( staff.getUsername() );
        staffResponse.fullName( staff.getFullName() );
        staffResponse.email( staff.getEmail() );
        staffResponse.role( staff.getRole() );

        return staffResponse.build();
    }

    @Override
    public void updateStaff(Staff staff, StaffUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        staff.setFullName( request.getFullName() );
        staff.setEmail( request.getEmail() );
        staff.setRole( request.getRole() );
    }

    private Long staffDepartmentId(Staff staff) {
        Department department = staff.getDepartment();
        if ( department == null ) {
            return null;
        }
        return department.getId();
    }

    private String staffDepartmentName(Staff staff) {
        Department department = staff.getDepartment();
        if ( department == null ) {
            return null;
        }
        return department.getName();
    }
}
