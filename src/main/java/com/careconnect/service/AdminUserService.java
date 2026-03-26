package com.careconnect.service;

import com.careconnect.entity.User;
import com.careconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User suspend(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setEnabled(false);
        return userRepository.save(user);
    }

    public User restore(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setEnabled(true);
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
