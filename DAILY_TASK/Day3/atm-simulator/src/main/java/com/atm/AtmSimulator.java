package com.atm;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class AtmSimulator {
    private static final String DEFAULT_PIN = "1234";
    private static final double INITIAL_BALANCE = 1000.00;

    private final Scanner scanner;
    private final java.io.PrintStream output;
    private double balance;
    private final List<String> transactions;
    private final Properties atmProperties;

    public AtmSimulator(Scanner scanner, java.io.PrintStream output) {
        this(scanner, output, loadProperties());
    }

    AtmSimulator(Scanner scanner, java.io.PrintStream output, Properties atmProperties) {
        this.scanner = scanner;
        this.output = output;
        this.atmProperties = atmProperties;
        this.balance = readInitialBalance();
        this.transactions = new ArrayList<String>();
    }

    public void run() {
        output.println("ATM environment: " + atmProperties.getProperty("atm.mode", "dev"));
        if (!authenticate()) {
            output.println("Access denied. The transaction has been terminated.");
            return;
        }

        boolean running = true;
        do {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    deposit();
                    break;
                case "2":
                    withdraw();
                    break;
                case "3":
                    showMiniStatement();
                    break;
                case "4":
                    output.println("Thank you for using the ATM.");
                    running = false;
                    break;
                default:
                    output.println("Invalid option. Please enter 1, 2, 3, or 4.");
            }
        } while (running);
    }

    private boolean authenticate() {
        for (int attempt = 1; attempt <= 3; attempt++) {
            output.print("Enter your 4-digit PIN: ");
            String pin = scanner.nextLine().trim();
            if (DEFAULT_PIN.equals(pin)) {
                output.println("Authentication successful.");
                return true;
            }

            output.println("Incorrect PIN. Attempt " + attempt + " of 3");
            if (attempt == 3) {
                return false;
            }
        }
        return false;
    }

    private void deposit() {
        double amount = readPositiveAmount("Deposit amount: ");
        if (amount <= 0) {
            return;
        }
        balance += amount;
        transactions.add("Deposit: $" + String.format("%.2f", amount));
        output.println("Deposit completed. New balance: $" + String.format("%.2f", balance));
    }

    private void withdraw() {
        double amount = readPositiveAmount("Withdraw amount: ");
        if (amount <= 0) {
            return;
        }
        if (amount > balance) {
            output.println("Insufficient funds. Transaction cancelled.");
            return;
        }

        balance -= amount;
        transactions.add("Withdrawal: $" + String.format("%.2f", amount));
        output.println("Withdrawal completed. New balance: $" + String.format("%.2f", balance));
    }

    private double readPositiveAmount(String prompt) {
        output.print(prompt);
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (amount <= 0) {
                output.println("Amount must be greater than zero.");
                return 0;
            }
            return amount;
        } catch (NumberFormatException exception) {
            output.println("Please enter a valid amount.");
            return 0;
        }
    }

    private void showMiniStatement() {
        output.println("Mini Statement");
        if (transactions.isEmpty()) {
            output.println("No transactions available.");
            return;
        }

        for (String transaction : transactions) {
            output.println(transaction);
        }
        output.println("Current balance: $" + String.format("%.2f", balance));
    }

    private void printMenu() {
        output.println("=== ATM Menu ===");
        output.println("1. Deposit money");
        output.println("2. Withdraw money");
        output.println("3. Mini Statement");
        output.println("4. Exit");
        output.print("Choose an option: ");
    }

    private double readInitialBalance() {
        String configuredBalance = atmProperties.getProperty("atm.initialBalance");
        if (configuredBalance == null) {
            return INITIAL_BALANCE;
        }
        try {
            double value = Double.parseDouble(configuredBalance);
            return value >= 0 ? value : INITIAL_BALANCE;
        } catch (NumberFormatException exception) {
            return INITIAL_BALANCE;
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        properties.setProperty("atm.initialBalance", String.valueOf(INITIAL_BALANCE));
        try (InputStream resource = AtmSimulator.class.getClassLoader().getResourceAsStream("atm.properties")) {
            if (resource != null) {
                properties.load(resource);
            }
        } catch (IOException exception) {
            properties.setProperty("atm.mode", "dev");
        }
        return properties;
    }

    public static void main(String[] args) {
        new AtmSimulator(new Scanner(System.in), new java.io.PrintStream(System.out)).run();
    }
}
