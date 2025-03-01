package com.xyzretail.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stationery {

    private int id;
    private String name;
    private double price;
    private int stockQuantity;

    
}