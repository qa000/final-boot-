package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDTO {
    private int bnum;               // 게시글 번호
    private String writer;          // 작성자
    private String title;           // 제목
    private String contents;        // 내용
    private String filename;        // 첨부 파일 이름
    private int likes;              // 좋아요 수
    private int hit;                // 조회수
    private int commentCount;       // 댓글 수
    private LocalDateTime regdate;  // 등록 날짜
}
