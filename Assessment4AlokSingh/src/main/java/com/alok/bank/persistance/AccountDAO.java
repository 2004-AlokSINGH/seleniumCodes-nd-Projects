package com.alok.bank.persistance;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alok.bank.entity.Account;


public class AccountDAO {

  private static final String URL = "jdbc:mysql://localhost:3306/dbdemo1";
  private static final String USER = "root";
  private static final String PASSWORD = "rootalok";

  public static Connection getConnection() throws SQLException {
      return DriverManager.getConnection(URL, USER, PASSWORD);
  }

  public double getAccountBalanceById(int accNo) throws SQLException {
      String query = "SELECT Balance FROM Account WHERE AccNo = ?";
      try (Connection connection = getConnection();
           PreparedStatement pstmt = connection.prepareStatement(query)) {
          pstmt.setInt(1, accNo);
          ResultSet resultSet = pstmt.executeQuery();
          if (resultSet.next()) {
              return resultSet.getDouble("Balance");
          }
      }
      throw new SQLException("No account exists with account number: " + accNo);
  }

  public boolean updateBalance(int accNo, double newBalance) throws SQLException {
      String query = "UPDATE Account SET Balance = ? WHERE AccNo = ?";
      try (Connection connection = getConnection();
           PreparedStatement pstmt = connection.prepareStatement(query)) {
          pstmt.setDouble(1, newBalance);
          pstmt.setInt(2, accNo);
          int rowsAffected = pstmt.executeUpdate();
          return rowsAffected > 0;
      }
  }
}


