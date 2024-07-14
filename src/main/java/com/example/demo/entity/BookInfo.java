package com.example.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bookinfo")
@Data
public class BookInfo {

    @Id
    private String itemid;
    private String title;
    private String author;
    private String categorylarge;
    private String categorysmall;
    private int pricestandard;
    private float reviewrating;
    private String publisher;
    private String publicationdate;
    private String coverimage;
    private String description;


}
