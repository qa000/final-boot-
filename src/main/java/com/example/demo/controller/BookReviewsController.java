package com.example.demo.controller;

import com.example.demo.dto.BookReviewDTO;
import com.example.demo.service.BookReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bookreviews")
public class BookReviewsController {

    @Autowired
    private BookReviewsService bookReviewsService;

    // 리뷰 목록 불러오기
    @GetMapping("/{bookid}")
    public List<BookReviewDTO> getReviewsByBookId(@PathVariable String bookid) {
        return bookReviewsService.getReviewsByBookId(bookid);
    }

    // 리뷰 추가
    @PostMapping
    public BookReviewDTO addReview(@RequestBody BookReviewDTO review) {
        return bookReviewsService.addReview(review);
    }

    // 리뷰 수정
    @PostMapping("/edit/{reviewid}")
    public BookReviewDTO updateReview(@PathVariable Long reviewid, @RequestBody BookReviewDTO review) {
        return bookReviewsService.updateReview(reviewid, review);
    }


}
