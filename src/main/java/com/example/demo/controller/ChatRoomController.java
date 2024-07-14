
package com.example.demo.controller;

import com.example.demo.model.ChatRoom;
import com.example.demo.model.Message;
import com.example.demo.model.Status;
import com.example.demo.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/chatrooms")
public class ChatRoomController {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatRoomService chatRoomService;

    public ChatRoomController(SimpMessagingTemplate messagingTemplate, ChatRoomService chatRoomService) {
        this.messagingTemplate = messagingTemplate;
        this.chatRoomService = chatRoomService;
    }

    @PostMapping("/create")
    @ResponseBody
    public ChatRoom createChatRoom(@RequestBody ChatRoom chatRoom) {
        chatRoom.setRoomId(UUID.randomUUID().toString());
        return chatRoomService.createChatRoom(chatRoom);
    }

    @GetMapping
    @ResponseBody
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomService.getAllChatRooms();
    }

    @DeleteMapping("/delete/{roomId}")
    @ResponseBody
    public void deleteChatRoom(@PathVariable String roomId) {
        chatRoomService.deleteChatRoom(roomId);
    }

    @PostMapping("/join/{roomId}")
    @ResponseBody
    public void joinChatRoom(@PathVariable String roomId, @RequestParam String username) {
        chatRoomService.getChatRoomById(roomId).ifPresent(room -> {
            room.getMembers().add(username);
            chatRoomService.createChatRoom(room); 

            // 환영 메시지 생성 및 전송
            Message welcomeMessage = new Message();
            welcomeMessage.setRoomId(roomId);
            welcomeMessage.setSenderName("고양이의 발자국");
            welcomeMessage.setReceiverName(roomId);
            welcomeMessage.setMessage(username + "님이 입장하셨습니다.");
//            welcomeMessage.setDate(new Date());
            welcomeMessage.setStatus(Status.JOIN);
            chatRoomService.saveMessage(welcomeMessage); // 메시지 저장
            messagingTemplate.convertAndSend("/topic/" + roomId, welcomeMessage);
        });
    }

    @PostMapping("/leave/{roomId}")
    @ResponseBody
    public void leaveChatRoom(@PathVariable String roomId, @RequestParam String username) {
        chatRoomService.getChatRoomById(roomId).ifPresent(room -> {
            room.getMembers().remove(username);
            chatRoomService.createChatRoom(room);

            // 퇴장 메시지 생성 및 전송
            Message leaveMessage = new Message();
            leaveMessage.setRoomId(roomId);
            leaveMessage.setSenderName("고양이의 발자국");
            leaveMessage.setReceiverName(roomId);
            leaveMessage.setMessage(username + "님이 퇴장하셨습니다.");
//            leaveMessage.setDate(new Date());
            leaveMessage.setStatus(Status.LEAVE);
            chatRoomService.saveMessage(leaveMessage); // 메시지 저장
            messagingTemplate.convertAndSend("/topic/" + roomId, leaveMessage);
        });
    }

    @PostMapping("/send/{roomId}")
    @ResponseBody
    public void sendMessage(@PathVariable String roomId, @RequestBody Message message) {
        message.setRoomId(roomId);
        System.out.println("Sending message: " + message);
        chatRoomService.saveMessage(message); // 메시지 저장
        messagingTemplate.convertAndSend("/topic/" + roomId, message);
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public Message send(@DestinationVariable String roomId, @Payload Message message) {
        message.setRoomId(roomId);
        System.out.println("Received message: " + message);
        chatRoomService.saveMessage(message); // 메시지 저장
        return message;
    }

    @GetMapping("/messages/{roomId}")
    @ResponseBody
    public List<Message> getMessagesByRoomId(@PathVariable String roomId) {
        return chatRoomService.getMessagesByRoomId(roomId);
    }
}
