
package com.example.demo.repository;

import com.example.demo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiverName(String receiverName);
    List<Message> findByReceiverNameIsNull();
    List<Message> findByRoomId(String roomId);
}
