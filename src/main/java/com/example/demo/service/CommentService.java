package com.example.demo.service;

import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // 댓글 조회
    public List<Comment> getCommentsByBoardnum(int boardnum) {
        return commentRepository.findByBoardnum(boardnum);
    }

    // 댓글 생성
    public Comment addComment(Comment comment) {
        comment.setCreatedat(new Timestamp(System.currentTimeMillis()));
        return commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(int commentid) {
        commentRepository.deleteById(commentid);
    }

    // 댓글 조회
    public Comment getCommentById(int commentid) {
        Optional<Comment> comment = commentRepository.findById(commentid);
        return comment.orElse(null);
    }
}
