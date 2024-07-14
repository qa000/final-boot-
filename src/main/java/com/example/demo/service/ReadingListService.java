package com.example.demo.service;

import com.example.demo.entity.ReadingList;
import com.example.demo.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingListService {

    @Autowired
    private ReadingListRepository readingListRepository;

    // 조회
    public List<ReadingList> getReadingListByUserId(String userId) {
        return readingListRepository.findByUserId(userId);
    }

    // 생성
    public ReadingList addReadingList(ReadingList readingList) {
        return readingListRepository.save(readingList);
    }
}
