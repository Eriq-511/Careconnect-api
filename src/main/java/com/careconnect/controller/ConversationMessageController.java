package com.careconnect.controller;

import com.careconnect.entity.Message;
import com.careconnect.repository.MessageRepository;
import com.careconnect.dto.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.careconnect.entity.User;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/conversations")
public class ConversationMessageController {
    @Autowired
    private MessageRepository messageRepository;

        @GetMapping("/{conversationId}/messages")
        public ResponseEntity<List<MessageResponse>> getMessages(@PathVariable Long conversationId) {
        List<MessageResponse> messages = messageRepository.findAll().stream()
            .filter(m -> m.getConversation().getId().equals(conversationId))
            .map(m -> new MessageResponse(
                m.getId(),
                m.getSender().getId(),
                m.getContent(),
                m.getSentAt(),
                m.getStatus().name()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(messages);
        }

    // Mark all delivered messages as seen for a conversation (called when user opens chat)
    @PutMapping("/{conversationId}/messages/seen")
    public ResponseEntity<?> markMessagesSeen(@PathVariable Long conversationId, @RequestParam Long userId) {
        List<Message> messages = messageRepository.findAll().stream()
                .filter(m -> m.getConversation().getId().equals(conversationId))
                .filter(m -> !m.getSender().getId().equals(userId))
                .filter(m -> m.getStatus() == Message.Status.DELIVERED)
                .toList();
        for (Message m : messages) {
            m.setStatus(Message.Status.SEEN);
            messageRepository.save(m);
        }
        return ResponseEntity.ok("Marked as seen");
    }
}
