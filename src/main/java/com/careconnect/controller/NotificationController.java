package com.careconnect.controller;

import com.careconnect.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/email")
    public ResponseEntity<?> sendEmail(@RequestParam String to,
                                       @RequestParam String subject,
                                       @RequestParam String text) {
        try {
            emailService.sendEmail(to, subject, text);
            return ResponseEntity.ok("Email sent");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
