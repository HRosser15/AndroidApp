package com.example.testapplication;

public class Account {
    int userID;
    private long accNum;
    private static long nextAccNum=10000;
    private double balance;


    public Account(int userID){
        balance=0;
        accNum=nextAccNum;
        nextAccNum++;
        this.userID = userID;
    }
    public Account(int userID, double newBalance){
        balance=newBalance;
        accNum=nextAccNum;
        nextAccNum++;
        this.userID = userID;
    }

    public long getAccNum() {
        return accNum;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double newBalance) {
        balance = newBalance;
    }
    public void withdraw(double withdrawalAmount){
        balance-=withdrawalAmount;
    }
    public void deposit(double depositAmount){
        balance+=depositAmount;
    }
}