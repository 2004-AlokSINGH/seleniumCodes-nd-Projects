package com.xyzretail.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    private int orderDetailId;
    private int orderId;
    private int itemId;
    private String category;
    private int quantity;
    private double price;
}
