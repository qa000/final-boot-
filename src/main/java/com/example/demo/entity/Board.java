package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bnum;

    private String writer;

    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String contents;

    private LocalDateTime regdate;

    private Integer hit = 0;

    private String filename;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] fileData;

    private Integer likes = 0;
}
