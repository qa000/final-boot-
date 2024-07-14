
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "message")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderName;
    private String receiverName;
    private String message;
    private String date;

    private String roomId; //그룹채팅때문에 추가

    @Enumerated(EnumType.STRING)
    private Status status;
}
