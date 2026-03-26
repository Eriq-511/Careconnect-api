package com.careconnect.service;

import com.careconnect.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {
    @Autowired
    private MessageRepository messageRepository;

    public long countMessages() {
        return messageRepository.count();
    }
}
