
package com.example.demo.service;

import com.example.demo.model.ChatRoom;
import com.example.demo.model.Message;
import com.example.demo.repository.ChatRoomRepository;
import com.example.demo.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private MessageRepository messageRepository;

    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    public void deleteChatRoom(String roomId) {
        chatRoomRepository.deleteById(roomId);
    }

    @Transactional
    public Optional<ChatRoom> getChatRoomById(String roomId) {
        return chatRoomRepository.findById(roomId);
    }

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    public List<Message> getMessagesByRoomId(String roomId) {
        return messageRepository.findAll().stream()
                .filter(message -> roomId.equals(message.getReceiverName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void joinChatRoom(String roomId, String username) {
        chatRoomRepository.findById(roomId).ifPresent(room -> {
            room.getMembers().add(username);
            chatRoomRepository.save(room);
        });
    }
}
