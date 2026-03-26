package com.careconnect.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;


public class RegisterRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 64)
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$",
        message = "Password must contain upper, lower, number, and special character"
    )
    private String password;

    @NotBlank
    @Pattern(regexp = "(?i)^(parent|babysitter)$", message = "Role must be parent or babysitter")
    private String role; // PARENT or BABYSITTER

    // Getters and setters with sanitization
    public String getEmail() { return email == null ? null : email.trim().toLowerCase(); }
        public void setEmail(String email) { this.email = email == null ? null : email.trim().toLowerCase(); }
    public String getPassword() { return password == null ? null : password.trim(); }
        public void setPassword(String password) { this.password = password == null ? null : password.trim(); }
    public String getRole() { return role == null ? null : role.trim().toUpperCase(); }
        public void setRole(String role) { this.role = role == null ? null : role.trim(); }
}
