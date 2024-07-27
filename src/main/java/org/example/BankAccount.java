package org.example;

import java.util.Scanner;

public class BankAccount {
    // Instance variables to store account details
    private double balance;
    private String accountHolderName;
    private String accountNumber;

    // Constructor with parameters
    public BankAccount(double balance, String accountHolderName, String accountNumber) {
        this.balance = balance;
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
    }

    // Constructor without parameters (for creating new accounts)
    public BankAccount() {
        Scanner scanner = new Scanner(System.in);
        // Prompt user for account details
        System.out.print("What is the name for the account? ");
        this.accountHolderName = scanner.nextLine();
        System.out.print("What is the beginning balance for the account? ");
        this.balance = scanner.nextDouble();
        this.accountNumber = generateAccountNumber();
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit of $" + amount + " successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive number.");
        }
    }

    // Method to withdraw money from the account
    public void withdrawal(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawal of $" + amount + " successful. New balance: $" + balance);
            } else {
                System.out.println("Insufficient funds. Current balance: $" + balance);
            }
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive number.");
        }
    }

    // Method to transfer money to another account
    public void transfer(BankAccount recipient, double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            recipient.deposit(amount);
            System.out.println("Transfer successful. $" + amount + " transferred to " + recipient.getAccountHolderName());
        } else if (amount <= 0) {
            System.out.println("Invalid transfer amount. Please enter a positive number.");
        } else {
            System.out.println("Insufficient funds for transfer. Current balance: $" + this.balance);
        }
    }

    // Getter methods
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    // Method to generate a unique account number
    private String generateAccountNumber() {
        return String.valueOf(System.currentTimeMillis());
    }
}
