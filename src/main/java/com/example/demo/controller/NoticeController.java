package com.example.demo.controller;

import com.example.demo.model.Notice;
import com.example.demo.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class NoticeController {

    @Autowired
    private NoticeRepository noticeRepository;

    @MessageMapping("/notice")
    @SendTo("/chatroom/notice")
    public Notice createNotice(@Payload Notice notice) {
        notice.setDate(new Date().toString());
        noticeRepository.save(notice);  // 공지를 데이터베이스에 저장
        return notice;
    }

    @GetMapping("/api/notices")
    public List<Notice> getNotices() {
        return noticeRepository.findAll();
    }

    @DeleteMapping("/api/notices")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearNotices() {
        noticeRepository.deleteAll();
    }
}
