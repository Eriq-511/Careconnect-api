package com.careconnect.service;

import com.careconnect.entity.BabysitterProfile;
import com.careconnect.repository.BabysitterProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.careconnect.service.EmailService;

import java.util.List;
import java.util.Optional;

@Service
public class BabysitterVerificationService {

    @Autowired
    private BabysitterProfileRepository babysitterProfileRepository;

    @Autowired
    private EmailService emailService;

    public List<BabysitterProfile> findAllPending() {
        return babysitterProfileRepository.findAll().stream()
                .filter(b -> !b.isVerified())
                .toList();
    }

    public Optional<BabysitterProfile> findById(Long id) {
        return babysitterProfileRepository.findById(id);
    }


    public BabysitterProfile approve(Long id) {
        BabysitterProfile profile = babysitterProfileRepository.findById(id).orElseThrow();
        profile.setVerified(true);
        BabysitterProfile saved = babysitterProfileRepository.save(profile);
        if (profile.getUser() != null && profile.getUser().getEmail() != null) {
            emailService.sendProfileVerificationNotification(profile.getUser().getEmail(), true);
        }
        return saved;
    }


    public BabysitterProfile reject(Long id) {
        BabysitterProfile profile = babysitterProfileRepository.findById(id).orElseThrow();
        profile.setVerified(false);
        BabysitterProfile saved = babysitterProfileRepository.save(profile);
        if (profile.getUser() != null && profile.getUser().getEmail() != null) {
            emailService.sendProfileVerificationNotification(profile.getUser().getEmail(), false);
        }
        return saved;
    }
}
