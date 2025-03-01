package com.xyzretail.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cosmetic {
    private int cosmeticId;
    private String name;
    private String brand;
    private double price;
    private int stock;
}