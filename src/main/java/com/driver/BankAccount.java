package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        if(sum < 0 || sum > 9*digits){
            throw new AccountNumberCannotGenerate("Account Number can not be generated");
        }
        StringBuilder accNo = new StringBuilder();
        int remaining = sum;
        for (int i=0;i<digits;i++){
            int dig = Math.min(9,remaining);
            remaining -= dig;
            accNo.append(dig);
        }
        return accNo.toString();
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(this.balance-amount < minBalance){
            throw new Exception("Insufficient Balance");
        }
        this.balance -= amount;
    }

}