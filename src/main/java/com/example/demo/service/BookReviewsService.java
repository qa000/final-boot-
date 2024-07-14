package com.example.demo.service;

import com.example.demo.dto.BookReviewDTO;
import com.example.demo.entity.BookReviews;
import com.example.demo.repository.BookReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookReviewsService {

    @Autowired
    private BookReviewsRepository bookReviewsRepository;

    // 리뷰 보기
    public List<BookReviewDTO> getReviewsByBookId(String bookid) {
        List<BookReviews> reviews = bookReviewsRepository.findByBookid(bookid);
        return reviews.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 리뷰 생성
    public BookReviewDTO addReview(BookReviewDTO reviewDTO) {
        BookReviews review = new BookReviews();
        review.setBookid(reviewDTO.getBookid());
        review.setMemberid(reviewDTO.getMemberid());
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setCreatedat(reviewDTO.getCreatedat());
        BookReviews savedReview = bookReviewsRepository.save(review);
        return convertToDto(savedReview);
    }

    private BookReviewDTO convertToDto(BookReviews review) {
        BookReviewDTO dto = new BookReviewDTO();
        dto.setReviewid(review.getReviewid());
        dto.setBookid(review.getBookid());
        dto.setMemberid(review.getMemberid());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedat(review.getCreatedat());
        return dto;
    }

    public BookReviewDTO updateReview(Long reviewid, BookReviewDTO reviewDTO) {
        BookReviews existingReview = bookReviewsRepository.findById(reviewid)
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다."));
        existingReview.setRating(reviewDTO.getRating());
        existingReview.setComment(reviewDTO.getComment());
        existingReview.setUpdatedat(LocalDateTime.now());
        BookReviews updatedReview = bookReviewsRepository.save(existingReview);
        return convertToDto(updatedReview);
    }
}
