package com.xyzretail.service;

import com.xyzretail.exceptions.EmptyCartException;

public interface ShoppingServiceInterface {
    // Display available items in a specific category
    void displayAvailableItemsByCategory(String category);

    // Add an item to the cart with the specified category, item ID, and quantity
    void addItemToCart(String category, int itemId, int quantity);

    // Generate and display the final bill for the items in the cart
    void generateFinalBill() throws EmptyCartException;

    // Register a new customer and return the customer ID
    int registerCustomer(String customerName, String contact, String email, String address);

    // Store customer and order details in the database
    void storeCustomerAndOrderDetails(String customerName, String contact, String email, String address) throws EmptyCartException;

    // Close the service and release resources (e.g., database connection)
    void closeService();
}
