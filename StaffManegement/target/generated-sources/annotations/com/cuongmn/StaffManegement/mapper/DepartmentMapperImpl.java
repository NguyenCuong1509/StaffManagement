package com.cuongmn.StaffManegement.mapper;

import com.cuongmn.StaffManegement.dto.request.DepartmentCreateRequest;
import com.cuongmn.StaffManegement.dto.request.DepartmentUpdateRequest;
import com.cuongmn.StaffManegement.dto.response.DepartmentResponse;
import com.cuongmn.StaffManegement.entity.Department;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-28T08:46:43+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.10 (Oracle Corporation)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public Department toDepartment(DepartmentCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Department.DepartmentBuilder department = Department.builder();

        department.description( request.getDescription() );
        department.name( request.getName() );

        return department.build();
    }

    @Override
    public DepartmentResponse toDepartmentResponse(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentResponse.DepartmentResponseBuilder departmentResponse = DepartmentResponse.builder();

        departmentResponse.id( department.getId() );
        departmentResponse.name( department.getName() );
        departmentResponse.description( department.getDescription() );

        return departmentResponse.build();
    }

    @Override
    public void updateDepartment(Department department, DepartmentUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        department.setName( request.getName() );
        department.setDescription( request.getDescription() );
    }
}
