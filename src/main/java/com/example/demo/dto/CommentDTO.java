// CommentDTO.java
package com.example.demo.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDTO {
    private int commentid;          // 댓글 고유 번호
    private int boardnum;           // 게시글 번호
    private String memberid;        // 사용자 ID
    private String content;         // 댓글 내용
    private Timestamp createdat;    // 댓글 쓴 날짜


}
