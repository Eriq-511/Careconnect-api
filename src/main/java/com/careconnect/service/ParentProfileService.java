package com.careconnect.service;

import com.careconnect.entity.ParentProfile;
import com.careconnect.repository.ParentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentProfileService {
    @Autowired
    private ParentProfileRepository parentProfileRepository;

    public List<ParentProfile> findAll() {
        return parentProfileRepository.findAll();
    }

    public Optional<ParentProfile> findById(Long id) {
        return parentProfileRepository.findById(id);
    }

    public ParentProfile save(ParentProfile profile) {
        return parentProfileRepository.save(profile);
    }

    public void delete(Long id) {
        parentProfileRepository.deleteById(id);
    }
}
