package com.careconnect.controller;

import com.careconnect.entity.BabysitterProfile;
import com.careconnect.service.BabysitterProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/babysitters")
public class BabysitterProfileController {
    @Autowired
    private BabysitterProfileService babysitterProfileService;

    @GetMapping
    public ResponseEntity<List<BabysitterProfile>> getAll() {
        return ResponseEntity.ok(babysitterProfileService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BabysitterProfile> getById(@PathVariable Long id) {
        Optional<BabysitterProfile> profile = babysitterProfileService.findById(id);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BabysitterProfile> create(@RequestBody BabysitterProfile profile) {
        return ResponseEntity.ok(babysitterProfileService.save(profile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BabysitterProfile> update(@PathVariable Long id, @RequestBody BabysitterProfile profile) {
        profile.setId(id);
        return ResponseEntity.ok(babysitterProfileService.save(profile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        babysitterProfileService.delete(id);
        return ResponseEntity.ok().build();
    }
}
