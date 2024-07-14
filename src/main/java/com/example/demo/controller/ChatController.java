
package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @MessageMapping("/private-message")
    public void recMessage(@Payload Message message) {
        if (message.getMessage() != null && !message.getMessage().trim().isEmpty()) {
            message.setDate(new Date().toString());
            messageRepository.save(message);  // 메시지를 데이터베이스에 저장
            simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
        }
    }

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message) {
        if (message.getMessage() != null && !message.getMessage().trim().isEmpty()) {
            message.setDate(new Date().toString());
            messageRepository.save(message);  // 메시지를 데이터베이스에 저장
            return message;
        }
        return null;
    }

    @GetMapping("/api/messages/user/{username}")
    public List<Message> getUserMessages(@PathVariable String username) {
        return messageRepository.findByReceiverName(username);
    }

    @GetMapping("/api/messages/public")
    public List<Message> getPublicMessages() {
        return messageRepository.findByReceiverNameIsNull();
    }

    @MessageMapping("/group-message")
    @SendTo("/chatroom/group")
    public Message receiveGroupMessage(@Payload Message message) {
        if (message.getMessage() != null && !message.getMessage().trim().isEmpty()) {
            message.setDate(new Date().toString());
            messageRepository.save(message);  // 메시지를 데이터베이스에 저장
            return message;
        }
        return null;
    }

}
