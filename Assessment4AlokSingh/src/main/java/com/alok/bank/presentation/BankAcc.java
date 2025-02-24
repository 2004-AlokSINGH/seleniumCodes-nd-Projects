package com.alok.bank.presentation;

import com.alok.bank.entity.Account;


import java.sql.SQLException;
import java.util.Scanner;

import com.alok.bank.persistance.AccountDAO;
import com.alok.bank.service.AccountService;

public class BankAcc {
	
	public void dotransfer() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Give the accountNo from which money transfer");
		int fromAccount=sc.nextInt();
		
		
		System.out.println("Give the accountNo from where money transfer");
		int toAccount=sc.nextInt();
		
		System.out.println("Give the amount you want to transfer");
		double amount=sc.nextDouble();
		
	      AccountDAO accountDAO = new AccountDAO();
	      AccountService accountService = new AccountService(accountDAO);

	     

	      try {
	          String result = accountService.transferMoney(fromAccount, toAccount, amount);
	          System.out.println(result);
	      } catch (SQLException e) {
	          System.err.println("Error during transaction: " + e.getMessage());
	      }
	}
	  
	
	
}
  