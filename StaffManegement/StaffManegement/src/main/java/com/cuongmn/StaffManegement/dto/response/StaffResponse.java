package com.cuongmn.StaffManegement.dto.response;

import com.cuongmn.StaffManegement.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StaffResponse {
    Long id;

    String username;

    String fullName;

    String email;

    Role role;

    Long departmentId;

    String departmentName;
}
