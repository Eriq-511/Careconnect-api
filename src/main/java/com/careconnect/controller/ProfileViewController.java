package com.careconnect.controller;

import com.careconnect.entity.ProfileView;
import com.careconnect.service.ProfileViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile-views")
public class ProfileViewController {
    @Autowired
    private ProfileViewService profileViewService;

    @GetMapping
    public ResponseEntity<List<ProfileView>> getAll() {
        return ResponseEntity.ok(profileViewService.findAll());
    }

    @PostMapping
    public ResponseEntity<ProfileView> create(@RequestBody ProfileView view) {
        return ResponseEntity.ok(profileViewService.save(view));
    }
}
