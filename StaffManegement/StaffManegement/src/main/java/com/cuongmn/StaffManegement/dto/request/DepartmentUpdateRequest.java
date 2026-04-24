package com.cuongmn.StaffManegement.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentUpdateRequest {
    @Size(min=2,max=100,message = "DEPARTMENT_NAME_INVALID")
    String name;
    String description;
}
