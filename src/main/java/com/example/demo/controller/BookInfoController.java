package com.example.demo.controller;

import com.example.demo.entity.BookInfo;
import com.example.demo.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookInfoController {

    @Autowired
    private BookInfoService bookInfoService;

    // 메인 페이지에서 상위 6권의 책 정보를 가져오는 API 엔드포인트
    @GetMapping("/main")
    public List<BookInfo> getTop6Books() {
        return bookInfoService.getTop6Books();
    }

    // 모든 책 정보를 가져오는 API 엔드포인트
    @GetMapping("/book")
    public List<BookInfo> getBookList() {
        return bookInfoService.getBookList();
    }

    // 특정 책 정보를 가져오는 API 엔드포인트
    @GetMapping("/book/view/{itemid}")
    public BookInfo getBookById(@PathVariable String itemid) {
        return bookInfoService.getBookById(itemid);
    }
}
