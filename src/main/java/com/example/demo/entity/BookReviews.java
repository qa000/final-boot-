package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "BookReviews")
public class BookReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewid;
    private String bookid;
    private String memberid;
    private int rating;
    private String comment;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;
}
