package com.cuongmn.StaffManegement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // Dùng để login
    @Column(nullable = false, unique = true)
    String username;

    // Lưu password đã hash bằng BCrypt, không lưu password gốc
    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String fullName;

    @Column(nullable = false, unique = true)
    String email;

    @Enumerated(EnumType.STRING)
    Role role;

    /*
     * Nhiều Staff thuộc một Department.
     * Hibernate sẽ tạo cột department_id trong bảng staff.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    Department department;
}