package com.example.demo.repository;

import com.example.demo.entity.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookInfoRepository extends JpaRepository<BookInfo, String> {
    List<BookInfo> findTop6ByOrderByReviewratingDesc();
}
