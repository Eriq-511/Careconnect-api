package com.careconnect.controller;

import com.careconnect.entity.User;
import com.careconnect.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(adminUserService.findAll());
    }

    @PostMapping("/{id}/suspend")
    public ResponseEntity<User> suspend(@PathVariable Long id) {
        return ResponseEntity.ok(adminUserService.suspend(id));
    }

    @PostMapping("/{id}/restore")
    public ResponseEntity<User> restore(@PathVariable Long id) {
        return ResponseEntity.ok(adminUserService.restore(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        adminUserService.delete(id);
        return ResponseEntity.ok().build();
    }
}
