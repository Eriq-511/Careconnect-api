package com.careconnect.service;

import com.careconnect.entity.User;
import com.careconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;

    public long countUsers() {
        return userRepository.count();
    }

    public long countBabysitters() {
        return userRepository.findAll().stream().filter(u -> "BABYSITTER".equals(u.getRole())).count();
    }

    public long countParents() {
        return userRepository.findAll().stream().filter(u -> "PARENT".equals(u.getRole())).count();
    }

    public long countAdmins() {
        return userRepository.findAll().stream().filter(u -> "ADMIN".equals(u.getRole())).count();
    }
}
