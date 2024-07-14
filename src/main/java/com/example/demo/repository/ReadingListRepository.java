package com.example.demo.repository;

import com.example.demo.entity.ReadingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingListRepository extends JpaRepository<ReadingList, Integer> {
    List<ReadingList> findByUserId(String userId);
}
