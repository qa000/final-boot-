
package com.example.demo.repository;

import com.example.demo.model.ChatRoom;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

}
