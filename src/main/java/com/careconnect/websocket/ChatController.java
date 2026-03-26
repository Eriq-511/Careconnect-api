package com.careconnect.websocket;

import com.careconnect.entity.Message;
import com.careconnect.entity.Conversation;
import com.careconnect.entity.User;
import com.careconnect.repository.ConversationRepository;
import com.careconnect.repository.MessageRepository;
import com.careconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ChatController {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private UserRepository userRepository;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/messages")
    public Message sendMessage(@Payload Message message) {
        message.setSentAt(new Date());
        message.setStatus(Message.Status.DELIVERED);
        return messageRepository.save(message);
    }
}
