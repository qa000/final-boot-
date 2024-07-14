
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.HashSet;
import java.util.Set;
@Data
@Entity
public class ChatRoom {

    @Id
    private String roomId;
    private String roomName;
    private String roomDescription;
    private String createdBy;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> members = new HashSet<>();

    // Getters and Setters
}


