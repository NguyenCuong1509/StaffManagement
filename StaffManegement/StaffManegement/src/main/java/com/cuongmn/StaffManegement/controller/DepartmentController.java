package com.cuongmn.StaffManegement.controller;

import com.cuongmn.StaffManegement.dto.request.DepartmentCreateRequest;
import com.cuongmn.StaffManegement.dto.request.DepartmentUpdateRequest;
import com.cuongmn.StaffManegement.dto.response.ApiResponse;
import com.cuongmn.StaffManegement.dto.response.DepartmentResponse;
import com.cuongmn.StaffManegement.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ApiResponse<DepartmentResponse> createDepartment(
            @RequestBody @Valid DepartmentCreateRequest request
    ) {
        return ApiResponse.<DepartmentResponse>builder()
                .message("Department created successfully")
                .result(departmentService.createDepartment(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<DepartmentResponse>> getDepartments() {
        return ApiResponse.<List<DepartmentResponse>>builder()
                .result(departmentService.getDepartments())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<DepartmentResponse> getDepartment(@PathVariable Long id) {
        return ApiResponse.<DepartmentResponse>builder()
                .result(departmentService.getDepartment(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<DepartmentResponse> updateDepartment(
            @PathVariable Long id,
            @RequestBody @Valid DepartmentUpdateRequest request
    ) {
        return ApiResponse.<DepartmentResponse>builder()
                .message("Department updated successfully")
                .result(departmentService.updateDepartment(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);

        return ApiResponse.<Void>builder()
                .message("Department deleted successfully")
                .build();
    }

}