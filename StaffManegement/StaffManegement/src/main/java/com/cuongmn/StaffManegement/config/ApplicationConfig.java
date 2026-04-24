package com.cuongmn.StaffManegement.config;


import com.cuongmn.StaffManegement.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final StaffRepository staffRepository;

    /*
     * PasswordEncoder là Bean dùng chung để:
     * 1. Hash password khi tạo Staff
     * 2. Check password khi login
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * Spring Security cần UserDetailsService để load user từ database.
     * Ở đây user chính là Staff.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> staffRepository.findByUsername(username)
                .map(staff -> User.builder()
                        .username(staff.getUsername())
                        .password(staff.getPassword())
                        .roles(staff.getRole().name())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Staff not found"));
    }
}