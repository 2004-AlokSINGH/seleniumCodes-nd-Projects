package com.xyzretail.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int bookId;
    private String title;
    private String author;
    private double price;
    private int stock;
}
