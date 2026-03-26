package com.careconnect.service;

import com.careconnect.entity.ProfileView;
import com.careconnect.repository.ProfileViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileViewService {
    @Autowired
    private ProfileViewRepository profileViewRepository;

    public List<ProfileView> findAll() {
        return profileViewRepository.findAll();
    }

    public ProfileView save(ProfileView view) {
        return profileViewRepository.save(view);
    }
}
