package com.xyzretail.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CD {
    private int cdId;
    private String title;
    private String artist;
    private double price;
    private int stock;
}