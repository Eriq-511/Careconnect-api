package com.careconnect.service;

import com.careconnect.entity.EmailVerificationToken;
import com.careconnect.entity.User;
import com.careconnect.repository.EmailVerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailVerificationService {
    @Autowired
    private EmailVerificationTokenRepository tokenRepository;

    public EmailVerificationToken createToken(User user, long expiryMillis) {
        String token = UUID.randomUUID().toString();
        Date expiryDate = new Date(System.currentTimeMillis() + expiryMillis);
        EmailVerificationToken verificationToken = new EmailVerificationToken(token, user, expiryDate);
        return tokenRepository.save(verificationToken);
    }

    public Optional<EmailVerificationToken> getByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void deleteToken(EmailVerificationToken token) {
        tokenRepository.delete(token);
    }
}
