package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "readinglist")
public class ReadingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "userid")
    private String userId;

    @Column(name = "bookid")
    private String bookId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReadingStatus status;

    @Column(name = "addedat")
    private Timestamp addedAt;


}
