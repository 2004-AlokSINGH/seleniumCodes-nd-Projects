package com.alok.bank.service;

import java.sql.SQLException;

import com.alok.bank.entity.Account;
import com.alok.bank.persistance.AccountDAO;



import java.sql.SQLException;

public class AccountService {
  private final AccountDAO accountDAO;

  public AccountService(AccountDAO accountDAO) {
      this.accountDAO = accountDAO;
  }

  public String transferMoney(int fromAccNo, int toAccNo, double amount) throws SQLException {
      double fromAccountBalance = accountDAO.getAccountBalanceById(fromAccNo);
      double toAccountBalance = accountDAO.getAccountBalanceById(toAccNo);

      System.out.println("Before Transfer:");
      System.out.println("From Account: " + fromAccNo + " | Balance: " + fromAccountBalance);
      System.out.println("To Account: " + toAccNo + " | Balance: " + toAccountBalance);

      if (fromAccountBalance < amount) {
          return "Transfer failed: Insufficient funds!";
      }

      double newFromBalance = fromAccountBalance - amount;
      double newToBalance = toAccountBalance + amount;

      boolean updatedFrom = accountDAO.updateBalance(fromAccNo, newFromBalance);
      boolean updatedTo = accountDAO.updateBalance(toAccNo, newToBalance);

      if (updatedFrom && updatedTo) {
          System.out.println("After Transfer:");
          System.out.println("From Account: " + fromAccNo + " | Balance: " + newFromBalance);
          System.out.println("To Account: " + toAccNo + " | Balance: " + newToBalance);
          return "Transfer successful!";
      } else {
          return "Transfer failed due to an unexpected error.";
      }
  }
}