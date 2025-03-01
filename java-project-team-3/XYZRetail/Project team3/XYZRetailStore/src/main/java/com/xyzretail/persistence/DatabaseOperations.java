//package com.xyzretail.persistence;
//
//import com.xyzretail.exceptions.NoStockException;
//
//import java.sql.*;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class DatabaseOperations implements DatabaseOperationsInterface {
//    private static final String URL = "jdbc:mysql://127.0.0.1:3306/psproject";
//    private static final String USER = "root";
//    private static final String PASSWORD = "Dollface.@21";
//    private Connection connection;
//
//    public DatabaseOperations() {
//        try {
//            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//        } catch (SQLException e) {
//            throw new RuntimeException("Error connecting to the database: " + e.getMessage());
//        }
//    }
//    @Override
//    public List<String> fetchItemsByCategory(String category) throws SQLException {
//        String query = "SELECT * FROM " + category; 
//        List<String> items = new ArrayList<>();
//
//        try (Statement stmt = connection.createStatement();
//             ResultSet resultSet = stmt.executeQuery(query)) {
//            while (resultSet.next()) {
//                int id = resultSet.getInt(1); // Use index instead of column name
//                String name = resultSet.getString(2); // Name might be at index 2
//                double price = resultSet.getDouble(3);
//          
//                int stockQuantity = resultSet.getInt(4);
//                
//                items.add(String.format("ID: %d, Name: %s, Price: %.2f, Stock: %d", id, name, price, stockQuantity));
//            }
//        }
//        return items;
//    }
//
//@Override
//    public void checkAndReduceStock(String category, int itemId, int quantity) throws SQLException, NoStockException {
//        String checkStockQuery = "SELECT stock_quantity FROM " + category + " WHERE id = ?";
//        String updateStockQuery = "UPDATE " + category + " SET stock_quantity = stock_quantity - ? WHERE id = ?";
//
//        try (PreparedStatement checkStmt = connection.prepareStatement(checkStockQuery)) {
//            checkStmt.setInt(1, itemId);
//            ResultSet resultSet = checkStmt.executeQuery();
//
//            if (resultSet.next()) {
//                int currentStock = resultSet.getInt("stock_quantity");
//                if (currentStock < quantity) {
//                    throw new NoStockException("Insufficient stock for item ID: " + itemId);
//                }
//            } else {
//                throw new NoStockException("Item not found in category: " + category);
//            }
//
//            try (PreparedStatement updateStmt = connection.prepareStatement(updateStockQuery)) {
//                updateStmt.setInt(1, quantity);
//                updateStmt.setInt(2, itemId);
//                updateStmt.executeUpdate();
//            }
//        }
//    }
//@Override
//    public String generateBill(Map<String, Integer> cart) throws SQLException {
//        StringBuilder bill = new StringBuilder("\n--- Your Bill ---\n");
//
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        bill.append("Date & Time: ").append(now.format(formatter)).append("\n\n");
//
//        double totalAmount = 0.0;
//
//        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
//            String key = entry.getKey();
//            String[] keyParts = key.split("-ID:");
//            String category = keyParts[0].trim().toLowerCase();
//            int itemId = Integer.parseInt(keyParts[1].trim());
//            int quantity = entry.getValue();
//
//            double unitPrice = getItemPrice(category, itemId);
//            double taxRate = getTaxRate(category);
//            double totalItemPrice = (unitPrice * quantity) + ((unitPrice * quantity * taxRate) / 100);
//
//            bill.append(category.substring(0, 1).toUpperCase())
//                    .append(category.substring(1))
//                    .append(" (Item ID: ")
//                    .append(itemId)
//                    .append(", Qty: ")
//                    .append(quantity)
//                    .append(") Price: Rs. ")
//                    .append(String.format("%.2f", totalItemPrice))
//                    .append("\n");
//
//            totalAmount += totalItemPrice;
//        }
//
//        bill.append("\nTotal Amount: Rs. ").append(String.format("%.2f", totalAmount))
//                .append("\nThank you for shopping at XYZ Retail!\n");
//
//        return bill.toString();
//    }
//@Override
//    public double getItemPrice(String category, int itemId) throws SQLException {
//        String query = "SELECT price FROM " + category + " WHERE id = ?";
//        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
//            pstmt.setInt(1, itemId);
//            ResultSet resultSet = pstmt.executeQuery();
//            if (resultSet.next()) {
//                return resultSet.getDouble("price");
//            } else {
//                throw new SQLException("Item not found in category " + category);
//            }
//        }
//    }
//@Override
//    public double getTaxRate(String category) {
//        switch (category.toLowerCase()) {
//            case "cds": return 10.0;
//            case "cosmetics": return 12.0;
//            case "books": return 0.0;
//            case "stationery": return 5.0;
//            default: return 0.0;
//        }
//    }
//@Override
//    public int insertCustomer(String name, String contact, String email, String address) throws SQLException {
//        String query = "INSERT INTO Customers (name, contact, email, address) VALUES (?, ?, ?, ?)";
//        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
//            pstmt.setString(1, name);
//            pstmt.setString(2, contact);
//            pstmt.setString(3, email);
//            pstmt.setString(4, address);
//            pstmt.executeUpdate();
//
//            ResultSet rs = pstmt.getGeneratedKeys();
//            if (rs.next()) {
//                return rs.getInt(1);
//            }
//            throw new SQLException("Failed to insert customer.");
//        }
//    }
//@Override
//    public int insertOrder(int customerId, double totalAmount) throws SQLException {
//        String query = "INSERT INTO Orders (customerId, totalAmount) VALUES (?, ?)";
//        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
//            pstmt.setInt(1, customerId);
//            pstmt.setDouble(2, totalAmount);
//            pstmt.executeUpdate();
//
//            ResultSet rs = pstmt.getGeneratedKeys();
//            if (rs.next()) {
//                return rs.getInt(1);
//            }
//            throw new SQLException("Failed to insert order.");
//        }
//    }
//@Override
//    public void insertOrderDetail(int orderId, int itemId, String category, int quantity, double price) throws SQLException {
//        String query = "INSERT INTO OrderDetails (orderId, itemId, category, quantity, price) VALUES (?, ?, ?, ?, ?)";
//        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
//            pstmt.setInt(1, orderId);
//            pstmt.setInt(2, itemId);
//            pstmt.setString(3, category);
//            pstmt.setInt(4, quantity);
//            pstmt.setDouble(5, price);
//            pstmt.executeUpdate();
//        }
//    }
//@Override
//    public void updateOrderTotalAmount(int orderId, double totalAmount) throws SQLException {
//	String query = "UPDATE Orders SET totalAmount = ? WHERE orderId = ?";
//        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
//            pstmt.setDouble(1, totalAmount);
//            pstmt.setInt(2, orderId);
//            pstmt.executeUpdate();
//        }
//    }
//@Override
//    public void closeConnection() {
//        try {
//            if (connection != null && !connection.isClosed()) {
//                connection.close();
//            }
//        } catch (SQLException e) {
//            System.out.println("Error closing the database connection: " + e.getMessage());
//        }
//    }
//}



package com.xyzretail.persistence;

import com.xyzretail.exceptions.InvalidInputException;
import com.xyzretail.exceptions.NoStockException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseOperations implements DatabaseOperationsInterface {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/psproject";
    private static final String USER = "root";
    private static final String PASSWORD = "Dollface.@21";
    private Connection connection;

    public DatabaseOperations() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database: " + e.getMessage());
        }
    }

    @Override
    public List<String> fetchItemsByCategory(String category) throws SQLException {
        String query = "SELECT * FROM " + category;
        List<String> items = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                int stockQuantity = resultSet.getInt(4);

                items.add(String.format("ID: %d, Name: %s, Price: %.2f, Stock: %d", id, name, price, stockQuantity));
            }
        }
        return items;
    }

    @Override
    public void checkAndReduceStock(String category, int itemId, int quantity) throws SQLException, NoStockException, InvalidInputException {
        if (quantity <= 0) {
            throw new InvalidInputException("Quantity must be a positive integer.");
        }

        String checkStockQuery = "SELECT stock_quantity FROM " + category + " WHERE id = ?";
        String updateStockQuery = "UPDATE " + category + " SET stock_quantity = stock_quantity - ? WHERE id = ?";

        try (PreparedStatement checkStmt = connection.prepareStatement(checkStockQuery)) {
            checkStmt.setInt(1, itemId);
            ResultSet resultSet = checkStmt.executeQuery();

            if (resultSet.next()) {
                int currentStock = resultSet.getInt("stock_quantity");
                if (currentStock < quantity) {
                    throw new NoStockException("Insufficient stock for item ID: " + itemId);
                }
            } else {
                throw new NoStockException("Item not found in category: " + category);
            }

            try (PreparedStatement updateStmt = connection.prepareStatement(updateStockQuery)) {
                updateStmt.setInt(1, quantity);
                updateStmt.setInt(2, itemId);
                updateStmt.executeUpdate();
            }
        }
    }

    @Override
    public String generateBill(Map<String, Integer> cart) throws SQLException, InvalidInputException {
        StringBuilder bill = new StringBuilder("\n--- Your Bill ---\n");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        bill.append("Date & Time: ").append(now.format(formatter)).append("\n\n");

        double totalAmount = 0.0;

        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String key = entry.getKey();
            String[] keyParts = key.split("-ID:");
            String category = keyParts[0].trim().toLowerCase();
            int itemId = Integer.parseInt(keyParts[1].trim());
            int quantity = entry.getValue();

            if (quantity <= 0) {
                throw new InvalidInputException("Quantity must be a positive integer.");
            }

            double unitPrice = getItemPrice(category, itemId);
            double taxRate = getTaxRate(category);
            double totalItemPrice = (unitPrice * quantity) + ((unitPrice * quantity * taxRate) / 100);

            bill.append(category.substring(0, 1).toUpperCase())
                    .append(category.substring(1))
                    .append(" (Item ID: ")
                    .append(itemId)
                    .append(", Qty: ")
                    .append(quantity)
                    .append(") Price: Rs. ")
                    .append(String.format("%.2f", totalItemPrice))
                    .append("\n");

            totalAmount += totalItemPrice;
        }

        bill.append("\nTotal Amount: Rs. ").append(String.format("%.2f", totalAmount))
                .append("\nThank you for shopping at XYZ Retail!\n");

        return bill.toString();
    }

    @Override
    public double getItemPrice(String category, int itemId) throws SQLException {
        String query = "SELECT price FROM " + category + " WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, itemId);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("price");
            } else {
                throw new SQLException("Item not found in category " + category);
            }
        }
    }

    @Override
    public double getTaxRate(String category) {
        switch (category.toLowerCase()) {
            case "cds": return 10.0;
            case "cosmetics": return 12.0;
            case "books": return 0.0;
            case "stationery": return 5.0;
            default: return 0.0;
        }
    }

    @Override
    public int insertCustomer(String name, String contact, String email, String address) throws SQLException {
        String query = "INSERT INTO Customers (name, contact, email, address) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, contact);
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            throw new SQLException("Failed to insert customer.");
        }
    }

    @Override
    public int insertOrder(int customerId, double totalAmount) throws SQLException {
        String query = "INSERT INTO Orders (customerId, totalAmount) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, customerId);
            pstmt.setDouble(2, totalAmount);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            throw new SQLException("Failed to insert order.");
        }
    }

    public void insertOrderDetail(int orderId, int itemId, String category, int quantity, double price) 
            throws SQLException, InvalidInputException {
        if (quantity <= 0) {
            throw new InvalidInputException("Quantity must be a positive integer.");
        }

        String query = "INSERT INTO OrderDetails (orderId, itemId, category, quantity, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, itemId);
            pstmt.setString(3, category);
            pstmt.setInt(4, quantity);
            pstmt.setDouble(5, price);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void updateOrderTotalAmount(int orderId, double totalAmount) throws SQLException {
        String query = "UPDATE Orders SET totalAmount = ? WHERE orderId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, totalAmount);
            pstmt.setInt(2, orderId);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing the database connection: " + e.getMessage());
        }
    }
}

