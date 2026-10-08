package com.atm.model;

import java.util.Objects;

public class BankAccount {
    private static int nextAccountSequence = 1000;

    private final String accountNumber;
    private double balance;

    public BankAccount() {
        this("ACCT-" + nextAccountSequence++);
    }

    public BankAccount(String accountNumber) {
        this(accountNumber, 0.0);
    }

    public BankAccount(String accountNumber, double balance) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number is required.");
        }
        this.accountNumber = accountNumber.trim();
        this.balance = validateBalance(balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public double deposit(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        return balance;
    }

    public double withdraw(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds.");
        }
        balance -= amount;
        return balance;
    }

    public static int getNextAccountNumber() {
        return nextAccountSequence;
    }

    private double validateBalance(double balance) {
        if (!Double.isFinite(balance) || balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative or non-finite.");
        }
        return balance;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BankAccount)) {
            return false;
        }
        BankAccount account = (BankAccount) other;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}
