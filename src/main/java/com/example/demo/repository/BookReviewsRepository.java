package com.example.demo.repository;

import com.example.demo.entity.BookReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookReviewsRepository extends JpaRepository<BookReviews, Long> {
    List<BookReviews> findByBookid(String bookid);
}
