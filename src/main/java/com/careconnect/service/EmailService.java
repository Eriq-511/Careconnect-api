package com.careconnect.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final String from;

    public EmailService(JavaMailSender mailSender, @Value("${app.email.from}") String from) {
        this.mailSender = mailSender;
        this.from = from;
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    // Admin action notification
    public void sendAdminActionNotification(String to, String action, String details) {
        String subject = "Admin Action Notification";
        String text = "An admin has performed the following action: " + action + "\nDetails: " + details;
        sendEmail(to, subject, text);
    }

    // Profile verification notification
    public void sendProfileVerificationNotification(String to, boolean approved) {
        String subject = "Profile Verification Result";
        String text = approved ?
            "Congratulations! Your profile has been verified and approved by the admin." :
            "We regret to inform you that your profile verification was not approved. Please review your information and try again.";
        sendEmail(to, subject, text);
    }

    // Password reset notification
    public void sendPasswordReset(String to, String resetLink) {
        String subject = "Password Reset Request";
        String text = "To reset your password, please click the following link: " + resetLink;
        sendEmail(to, subject, text);
    }

    // Messaging event notification
    public void sendMessageNotification(String to, String fromUser, String messagePreview) {
        String subject = "New Message Received";
        String text = "You have received a new message from " + fromUser + ":\n" + messagePreview;
        sendEmail(to, subject, text);
    }
}
