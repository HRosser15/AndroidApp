package com.example.testapplication;

public class Savings extends Account {
    int depNum=0; //count of deposits
    int year=0; //Number of years as client
    public Savings(int userID){
        super(userID);
    }

    @Override
    public void withdraw(double withdrawalAmount){
        if (getBalance()-withdrawalAmount<500){
            super.withdraw(withdrawalAmount+10);
            //subtract withdrawalAmount and fee
            System.out.println("Charging an overdraft fee of $10 because you are below $500");
        }
        else{
            super.withdraw(withdrawalAmount);
        }
    }
    public void deposit(double depositAmount){
        depNum++;
        System.out.println("This is deposit "+depNum+" to this account");
        if (depNum>5){
            super.deposit(depositAmount-10);
            System.out.println("Charging a fee of $10");
        }
        else{
            super.deposit(depositAmount);
        }
    }
    public void interest(){
        year++;
        double interestRate = .015 * year;
        System.out.printf("Customer earned $%.2f in interest\n", (interestRate*getBalance()));
        super.deposit(interestRate*getBalance());
    }
    @Override
    public String toString(){
        return String.format("Your balance for savings "+getAccNum()+" is $%.2f", getBalance());
    }
}