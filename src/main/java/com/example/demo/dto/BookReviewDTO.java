package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookReviewDTO {
    private Long reviewid;              // 리뷰 고유 번호
    private String bookid;              // 도서 ID
    private String memberid;            // 사용자
    private int rating;                 // 평점
    private String comment;             // 리뷰
    private LocalDateTime createdat;    // 쓴 날짜
    private LocalDateTime updatedat; // 수정 시간 필드 추가
}
