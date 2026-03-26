package com.careconnect.controller;

import com.careconnect.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminStatsController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/stats")
    public ResponseEntity<?> getStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalUsers", adminService.countUsers());
        stats.put("babysitters", adminService.countBabysitters());
        stats.put("parents", adminService.countParents());
        stats.put("admins", adminService.countAdmins());
        return ResponseEntity.ok(stats);
    }
}
