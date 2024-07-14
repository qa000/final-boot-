package com.example.demo.controller;

import com.example.demo.entity.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 목록
    @GetMapping("/board/{boardnum}")
    public List<Comment> getCommentsByBoardnum(@PathVariable int boardnum) {
        return commentService.getCommentsByBoardnum(boardnum);
    }

    // 댓글 추가
    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    // 댓글 수정
    @PutMapping("/{commentid}")
    public Comment updateComment(@PathVariable int commentid, @RequestBody Comment updatedComment) {
        Comment comment = commentService.getCommentById(commentid);
        if (comment != null) {
            comment.setContent(updatedComment.getContent());
            return commentService.addComment(comment);
        } else {
            return null;
        }
    }

    // 댓글 삭제
    @DeleteMapping("/{commentid}")
    public void deleteComment(@PathVariable int commentid) {
        commentService.deleteComment(commentid);
    }
}
