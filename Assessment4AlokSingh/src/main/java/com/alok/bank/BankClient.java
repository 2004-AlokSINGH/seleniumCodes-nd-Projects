package com.alok.bank;

import com.alok.bank.presentation.BankAcc;



/*
 * 
 * Create a table called Account with AccNo, AccName, AccType and Balance. 
 * Now consider a transfer of money from AccNo 1 to AccNo2. 
 * Display the AccNo and Balance before the transfer and after the transfer.
 * Try both the scenarios of transfer is successful and transfer is failure.
 */


//mysql> use dbdemo1;
//Database changed
//mysql>
//mysql> CREATE TABLE Account (
//    -> AccNo INT PRIMARY KEY,
//    -> AccName VARCHAR(100),
//    -> AccType VARCHAR(50),
//    -> Balance DECIMAL(10,2)
//    -> );
//Query OK, 0 rows affected (0.08 sec)
public class BankClient {

	public static void main(String[] args) {
		BankAcc bankAcc= new BankAcc();
		bankAcc.dotransfer();

	}

}
