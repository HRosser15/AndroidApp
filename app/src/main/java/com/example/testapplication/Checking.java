package com.example.testapplication;

public class Checking extends Account {

    public Checking(int userID){
        super(userID);
    }

    @Override
    public void withdraw(double withdrawalAmount){

        if (getBalance()-withdrawalAmount<0){
            super.withdraw(withdrawalAmount+20);
            //subtract withdrawalAmount and fee from balance.
            System.out.println("Charging an overdraft fee of $20 because account is below $0");
        }
        else{
            super.withdraw(withdrawalAmount);
        }
    }
    @Override
    public String toString(){
        return String.format("Your balance for checking "+getAccNum()+" is $%.2f", getBalance());
    }
}