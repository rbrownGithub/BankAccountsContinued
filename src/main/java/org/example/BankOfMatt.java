package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class BankOfMatt {
    // ArrayList to store all bank accounts
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello! Welcome to the Bank of Matt!");

        while (true) {
            System.out.println("Are you an existing customer? (-1 to exit)");
            System.out.println("1. Yes");
            System.out.println("2. No");

            int choice = -2; // Initialize with an invalid value
            boolean validInput = false;

            while (!validInput) {
                try {
                    String input = scanner.nextLine();
                    choice = Integer.parseInt(input);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            if (choice == -1) {
                break;
            } else if (choice == 1) {
                BankAccount account = selectAccount();
                if (account != null) {
                    mainMenu(account);
                }
            } else if (choice == 2) {
                System.out.println("Let's make a new account!");
                BankAccount newAccount = new BankAccount();
                accounts.add(newAccount);
                System.out.println("Account created successfully!");
                mainMenu(newAccount);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Thank you for using the Bank of Matt!");
    }

    // Method to display and handle the main menu for a specific account
    private static void mainMenu(BankAccount account) {
        while (true) {
            // Display menu options
            System.out.println("\nHello " + account.getAccountHolderName() + "!");
            System.out.println("Welcome to the Main Menu, what would you like to do today?");
            System.out.println("1. To check account balance");
            System.out.println("2. To make a withdraw");
            System.out.println("3. To make a deposit");
            System.out.println("4. To make a transfer to an another account");
            System.out.println("0. To exit.");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Handle user's choice
            switch (choice) {
                case 1:
                    System.out.println("Your current balance is: $" + String.format("%.2f", account.getBalance()));
                    break;
                case 2:
                    System.out.print("Enter the amount to withdraw: $");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdrawal(withdrawalAmount);
                    break;
                case 3:
                    System.out.print("Enter the amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    performTransfer(account);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to handle the transfer process
    private static void performTransfer(BankAccount sourceAccount) {
        System.out.print("Please enter the account number to transfer to: ");
        String targetAccountNumber = scanner.nextLine();

        // Find the target account
        BankAccount targetAccount = findAccount(targetAccountNumber);
        if (targetAccount == null) {
            System.out.println("Account doesn't exist");
            return;
        }

        // Get transfer amount and perform transfer
        System.out.print("Please enter the amount to transfer: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        sourceAccount.transfer(targetAccount, amount);
    }

    // Method to select an existing account
    private static BankAccount selectAccount() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found. Please create a new account.");
            return null;
        }

        System.out.println("Select an account:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i).getAccountHolderName());
        }

        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        if (choice > 0 && choice <= accounts.size()) {
            return accounts.get(choice - 1);
        } else {
            System.out.println("Invalid choice. Please try again.");
            return null;
        }
    }

    // Method to find an account by account number
    private static BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
