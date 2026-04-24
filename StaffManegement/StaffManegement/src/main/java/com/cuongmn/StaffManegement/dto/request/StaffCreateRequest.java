package com.cuongmn.StaffManegement.dto.request;

import com.cuongmn.StaffManegement.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.aspectj.bridge.IMessage;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StaffCreateRequest {
    @NotBlank(message = "USERNAME_REQUIRED")
    @Size(min=3,max = 50,message = "USER_INVALID")
    String username;
    @NotBlank(message = "PASSWORD_REQUIRED")
    @Size(min = 6, max = 100, message = "PASSWORD_INVALID")
    String password;

    @NotBlank(message = "FULL_NAME_REQUIRED")
    String fullName;

    @Email(message = "EMAIL_INVALID")
    @NotBlank(message = "EMAIL_REQUIRED")
    String email;

    @NotNull(message = "ROLE_REQUIRED")
    Role role;

    @NotNull(message = "DEPARTMENT_REQUIRED")
    Long departmentId;
}
