package com.careconnect.controller;

import com.careconnect.entity.ParentProfile;
import com.careconnect.dto.request.ParentProfileRequest;
import com.careconnect.service.ParentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parents")
public class ParentProfileController {
    @Autowired
    private ParentProfileService parentProfileService;

    @GetMapping
    public ResponseEntity<List<ParentProfile>> getAll() {
        return ResponseEntity.ok(parentProfileService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentProfile> getById(@PathVariable Long id) {
        Optional<ParentProfile> profile = parentProfileService.findById(id);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ParentProfile> create(@Valid @RequestBody ParentProfileRequest request) {
        ParentProfile profile = new ParentProfile();
        profile.setFullName(request.getName());
        // Map other fields as needed
        return ResponseEntity.ok(parentProfileService.save(profile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParentProfile> update(@PathVariable Long id, @Valid @RequestBody ParentProfileRequest request) {
        ParentProfile profile = parentProfileService.findById(id).orElseThrow();
        profile.setFullName(request.getName());
        // Map other fields as needed
        return ResponseEntity.ok(parentProfileService.save(profile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        parentProfileService.delete(id);
        return ResponseEntity.ok().build();
    }
}
