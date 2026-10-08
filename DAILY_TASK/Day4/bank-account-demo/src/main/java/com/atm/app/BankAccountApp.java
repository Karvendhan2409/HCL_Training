package com.atm.app;

import com.atm.model.BankAccount;
import com.atm.service.BankAccountService;

public class BankAccountApp {
    private final BankAccountService service = new BankAccountService();

    public static void main(String[] args) {
        new BankAccountApp().run();
    }

    public void run() {
        BankAccount account = service.createAccount("ACCT-1000", 1000.0);
        System.out.println("Account " + account.getAccountNumber()
                + " created with balance $" + account.getBalance());

        service.deposit(account, 250.0);
        service.withdraw(account, 150.0);

        System.out.println("Final balance: $" + account.getBalance());
    }
}
