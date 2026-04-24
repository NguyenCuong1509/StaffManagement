package com.cuongmn.StaffManegement.dto.request;

import com.cuongmn.StaffManegement.entity.Role;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StaffUpdateRequest {

    String fullName;

    @Email(message = "EMAIL_INVALID")
    String email;

    Role role;

    Long departmentId;
}