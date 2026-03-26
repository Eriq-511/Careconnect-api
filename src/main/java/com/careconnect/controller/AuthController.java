package com.careconnect.controller;

import com.careconnect.dto.request.LoginRequest;
import com.careconnect.dto.request.RegisterRequest;
import com.careconnect.dto.response.AuthResponse;
import com.careconnect.entity.User;
import com.careconnect.repository.UserRepository;
import com.careconnect.security.JwtUtil;
import com.careconnect.service.EmailVerificationService;
import com.careconnect.service.EmailService;
import com.careconnect.entity.EmailVerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmailVerificationService emailVerificationService;
    @Autowired
    private EmailService emailService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole().toUpperCase());
        user.setEnabled(false); // Not enabled until verified
        userRepository.save(user);

        // Create verification token and send email
        EmailVerificationToken verificationToken = emailVerificationService.createToken(user, 24 * 60 * 60 * 1000); // 24h expiry
        String verifyUrl = "http://localhost:5173/verify-email?token=" + verificationToken.getToken();
        String subject = "Verify your email address";
        String text = "Please verify your email by clicking the following link: " + verifyUrl;
        emailService.sendEmail(user.getEmail(), subject, text);

        return ResponseEntity.ok("Registration successful. Please check your email to verify your account.");
    }

    @GetMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String token) {
        return emailVerificationService.getByToken(token)
                .map(verificationToken -> {
                    if (verificationToken.getExpiryDate().before(new java.util.Date())) {
                        return ResponseEntity.badRequest().body("Verification token expired");
                    }
                    User user = verificationToken.getUser();
                    user.setEnabled(true);
                    userRepository.save(user);
                    emailVerificationService.deleteToken(verificationToken);
                    return ResponseEntity.ok("Email verified successfully. You can now log in.");
                })
                .orElseGet(() -> ResponseEntity.badRequest().body("Invalid verification token"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return ResponseEntity.ok(new AuthResponse(token, user.getRole()));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("Invalid token");
        }
        String email = jwtUtil.getEmailFromToken(token);
        User user = userRepository.findByEmail(email).orElseThrow();
        return ResponseEntity.ok(user);
    }
}
