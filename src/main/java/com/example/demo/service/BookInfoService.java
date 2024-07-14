package com.example.demo.service;

import com.example.demo.entity.BookInfo;
import com.example.demo.repository.BookInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookInfoService {

    @Autowired
    private BookInfoRepository bookInfoRepository;

    //  높은 상위 6권의 책을 조회
    public List<BookInfo> getTop6Books() {
        return bookInfoRepository.findTop6ByOrderByReviewratingDesc();
    }

    // 모든 책 정보를 조회
    public List<BookInfo> getBookList() {
        return bookInfoRepository.findAll();
    }

    // 책 ID로 책 정보를 조회
    public BookInfo getBookById(String itemid) {
        return bookInfoRepository.findById(itemid).orElse(null);
    }
}
