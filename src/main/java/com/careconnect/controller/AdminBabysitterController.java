package com.careconnect.controller;

import com.careconnect.entity.BabysitterProfile;
import com.careconnect.service.BabysitterVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/babysitters")
public class AdminBabysitterController {
    @Autowired
    private BabysitterVerificationService babysitterVerificationService;

    @GetMapping("/pending")
    public ResponseEntity<List<BabysitterProfile>> getPending() {
        return ResponseEntity.ok(babysitterVerificationService.findAllPending());
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<BabysitterProfile> approve(@PathVariable Long id) {
        return ResponseEntity.ok(babysitterVerificationService.approve(id));
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<BabysitterProfile> reject(@PathVariable Long id) {
        return ResponseEntity.ok(babysitterVerificationService.reject(id));
    }
}
