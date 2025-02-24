package com.alok.bank.entity;

public class Account {
    private int accNo;
    private String accName;
    private String accType;
    private double balance;

    public Account() {}

    public Account(int accNo, String accName, String accType, double balance) {
        this.accNo = accNo;
        this.accName = accName;
        this.accType = accType;
        this.balance = balance;
    }

    public int getAccNo() { return accNo; }
    public void setAccNo(int accNo) { this.accNo = accNo; }

    public String getAccName() { return accName; }
    public void setAccName(String accName) { this.accName = accName; }

    public String getAccType() { return accType; }
    public void setAccType(String accType) { this.accType = accType; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}