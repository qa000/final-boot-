package com.example.demo.controller;

import com.example.demo.dto.ReadingListDTO;
import com.example.demo.entity.ReadingList;
import com.example.demo.entity.ReadingStatus;
import com.example.demo.service.ReadingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReadingListController {

    @Autowired
    private ReadingListService readingListService;

    // 읽기 목록
    @GetMapping("/readinglist/{userId}")
    public List<ReadingList> getReadingListByUserId(@PathVariable String userId) {
        return readingListService.getReadingListByUserId(userId);
    }

    // 읽음 상태
    @PostMapping("/readinglist")
    public ReadingList addReadingList(@RequestBody ReadingListDTO readingListDTO) {
        ReadingList readingList = new ReadingList();
        readingList.setUserId(readingListDTO.getUserId());
        readingList.setBookId(readingListDTO.getBookId());
        readingList.setStatus(ReadingStatus.valueOf(readingListDTO.getStatus()));
        readingList.setAddedAt(readingListDTO.getAddedat());
        return readingListService.addReadingList(readingList);
    }
}
