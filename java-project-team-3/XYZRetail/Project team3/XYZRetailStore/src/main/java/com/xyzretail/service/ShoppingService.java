package com.xyzretail.service;

import com.xyzretail.persistence.DatabaseOperations;
import com.xyzretail.exceptions.EmptyCartException;
import com.xyzretail.exceptions.NoStockException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingService implements ShoppingServiceInterface {
    private final DatabaseOperations dbOps;
    private final Map<String, Integer> cart;

    public ShoppingService() {
        dbOps = new DatabaseOperations();
        cart = new HashMap<>();
    }

    @Override
    public void displayAvailableItemsByCategory(String category) {
        try {
            List<String> items = dbOps.fetchItemsByCategory(category);
            if (items.isEmpty()) {
                System.out.println("No items available in this category.");
                return;
            }

            System.out.printf("Available Items in %s Category:\n", category);
            for (String item : items) {
                System.out.println(item);
            }
        } catch (Exception e) {
            System.out.println("Error fetching items: " + e.getMessage());
        }
    }

    @Override
    public void addItemToCart(String category, int itemId, int quantity) {
        try {
            dbOps.checkAndReduceStock(category, itemId, quantity);
            String itemKey = category.toLowerCase() + "-ID:" + itemId;
            cart.put(itemKey, cart.getOrDefault(itemKey, 0) + quantity);
            System.out.println("Item successfully added to cart!");
        } catch (NoStockException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error adding item to cart: " + e.getMessage());
        }
    }

    @Override
    public void generateFinalBill() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Please add items before generating a bill.");
            return;
        }

        try {
            String bill = dbOps.generateBill(cart);
            System.out.println("=== Final Bill ===");
            System.out.println(bill);
        } catch (Exception e) {
            System.out.println("Error generating bill: " + e.getMessage());
        }
    }

    @Override
    public int registerCustomer(String customerName, String contact, String email, String address) {
        try {
            return dbOps.insertCustomer(customerName, contact, email, address);
        } catch (Exception e) {
            System.out.println("Error registering customer: " + e.getMessage());
            return -1; // Indicates failure
        }
    }

    @Override
    public void storeCustomerAndOrderDetails(String customerName, String contact, String email, String address)
            throws EmptyCartException {
        if (cart.isEmpty()) {
            throw new EmptyCartException("Cart is empty. Please add items before placing an order.");
        }

        try {
            int customerId = registerCustomer(customerName, contact, email, address);
            if (customerId == -1) {
                System.out.println("Error registering customer. Order not placed.");
                return;
            }

            double totalAmount = 0.0;
            int orderId = dbOps.insertOrder(customerId, totalAmount);

            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                String[] categoryAndId = entry.getKey().split("-ID:");
                String category = categoryAndId[0];
                int itemId = Integer.parseInt(categoryAndId[1]);
                int quantity = entry.getValue();
                double price = dbOps.getItemPrice(category, itemId);

                totalAmount += price * quantity;
                dbOps.insertOrderDetail(orderId, itemId, category, quantity, price);
            }

            dbOps.updateOrderTotalAmount(orderId, totalAmount);

            // Generate and display the final bill
            String finalBill = dbOps.generateBill(cart);
            System.out.println(finalBill);

            cart.clear(); // Clear cart after successful order placement
            System.out.printf("Order successfully placed for customer: %s. Total Amount: %.2f%n", customerName, totalAmount);
        } catch (Exception e) {
            System.out.println("Error storing order details: " + e.getMessage());
        }
    }

    @Override
    public void closeService() {
        dbOps.closeConnection();
        System.out.println("Service closed. Database connection terminated.");
    }
}
