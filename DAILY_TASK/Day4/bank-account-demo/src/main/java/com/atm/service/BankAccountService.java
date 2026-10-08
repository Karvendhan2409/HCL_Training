package com.atm.service;

import com.atm.model.BankAccount;

public class BankAccountService {
    public BankAccount createAccount(String accountNumber, double initialBalance) {
        return new BankAccount(accountNumber, initialBalance);
    }

    public double deposit(BankAccount account, double amount) {
        return account.deposit(amount);
    }

    public double withdraw(BankAccount account, double amount) {
        return account.withdraw(amount);
    }
}
