package com.xyzretail.persistence;

import com.xyzretail.exceptions.InvalidInputException;
import com.xyzretail.exceptions.NoStockException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DatabaseOperationsInterface {

    List<String> fetchItemsByCategory(String category) throws SQLException;

    void checkAndReduceStock(String category, int itemId, int quantity) throws SQLException, NoStockException, InvalidInputException;

    String generateBill(Map<String, Integer> cart) throws SQLException, InvalidInputException;

    double getItemPrice(String category, int itemId) throws SQLException;

    double getTaxRate(String category);

    int insertCustomer(String name, String contact, String email, String address) throws SQLException;

    int insertOrder(int customerId, double totalAmount) throws SQLException;

    void insertOrderDetail(int orderId, int itemId, String category, int quantity, double price) throws SQLException, InvalidInputException;

    void updateOrderTotalAmount(int orderId, double totalAmount) throws SQLException;

    void closeConnection();
}
