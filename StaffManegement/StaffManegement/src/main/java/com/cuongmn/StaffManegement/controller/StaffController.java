package com.cuongmn.StaffManegement.controller;

import com.cuongmn.StaffManegement.dto.request.StaffCreateRequest;
import com.cuongmn.StaffManegement.dto.request.StaffUpdateRequest;
import com.cuongmn.StaffManegement.dto.response.ApiResponse;
import com.cuongmn.StaffManegement.dto.response.StaffResponse;
import com.cuongmn.StaffManegement.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public ApiResponse<StaffResponse> createStaff(
            @RequestBody @Valid StaffCreateRequest request
    ) {
        return ApiResponse.<StaffResponse>builder()
                .message("Staff created successfully")
                .result(staffService.createStaff(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<StaffResponse>> getStaffList() {
        return ApiResponse.<List<StaffResponse>>builder()
                .result(staffService.getStaffList())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<StaffResponse> getStaff(@PathVariable Long id) {
        return ApiResponse.<StaffResponse>builder()
                .result(staffService.getStaff(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<StaffResponse> updateStaff(
            @PathVariable Long id,
            @RequestBody @Valid StaffUpdateRequest request
    ) {
        return ApiResponse.<StaffResponse>builder()
                .message("Staff updated successfully")
                .result(staffService.updateStaff(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);

        return ApiResponse.<Void>builder()
                .message("Staff deleted successfully")
                .build();
    }
}