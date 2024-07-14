package com.example.demo.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReadingListDTO {
    private String userId;      // 사용자 ID
    private String bookId;      // 도서 ID
    private String status;      // 읽음 상태
    private Timestamp addedat;  // 생성 날짜

}
