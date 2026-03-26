package com.careconnect.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class LoginRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    // Getters and setters with sanitization
    public String getEmail() { return email == null ? null : email.trim().toLowerCase(); }
        public void setEmail(String email) { this.email = email == null ? null : email.trim().toLowerCase(); }
    public String getPassword() { return password == null ? null : password.trim(); }
        public void setPassword(String password) { this.password = password == null ? null : password.trim(); }
}
