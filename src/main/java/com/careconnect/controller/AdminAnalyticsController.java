package com.careconnect.controller;

import com.careconnect.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/analytics")
public class AdminAnalyticsController {
    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/messages")
    public ResponseEntity<Long> getMessageCount() {
        return ResponseEntity.ok(analyticsService.countMessages());
    }
}
