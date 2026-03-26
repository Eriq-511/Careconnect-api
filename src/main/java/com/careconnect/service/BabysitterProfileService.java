package com.careconnect.service;

import com.careconnect.entity.BabysitterProfile;
import com.careconnect.repository.BabysitterProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BabysitterProfileService {
    @Autowired
    private BabysitterProfileRepository babysitterProfileRepository;

    public List<BabysitterProfile> findAll() {
        return babysitterProfileRepository.findAll();
    }

    public Optional<BabysitterProfile> findById(Long id) {
        return babysitterProfileRepository.findById(id);
    }

    public BabysitterProfile save(BabysitterProfile profile) {
        return babysitterProfileRepository.save(profile);
    }

    public void delete(Long id) {
        babysitterProfileRepository.deleteById(id);
    }
}
