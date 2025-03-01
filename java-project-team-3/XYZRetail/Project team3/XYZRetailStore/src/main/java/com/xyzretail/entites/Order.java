package com.xyzretail.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int orderId;
    private int customerId;
    private LocalDateTime orderDate;
    private double totalAmount;
}
