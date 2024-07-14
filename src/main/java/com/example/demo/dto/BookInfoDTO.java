package com.example.demo.dto;


import lombok.Data;

@Data
public class BookInfoDTO {
    private String itemid;                  // 도서 ID
    private String title;                   // 도서 제목
    private String author;                  // 도서 작가
    private String categoryname_large;      // 도서 카테고리
    private String categoryname_small;      // 도서 카테고리
    private int priceStandard;              // 도서 가격
    private float customer_review_rank;     // 도서 리뷰
    private String publisher;               // 도서 출판사
    private String pubDate;                 // 도서 출판일
    private String cover;                   // 도서 이미지
    private String description;             // 도서 설명


}
