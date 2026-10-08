package com.atm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.atm.model.BankAccount;
import org.junit.jupiter.api.Test;

class BankAccountServiceTest {
    @Test
    void serviceProcessesTransactions() {
        BankAccountService service = new BankAccountService();
        BankAccount account = service.createAccount("ACCT-4000", 100.0);

        assertEquals(150.0, service.deposit(account, 50.0));
        assertEquals(50.0, service.withdraw(account, 100.0));
    }

    @Test
    void serviceRejectsInsufficientFunds() {
        BankAccountService service = new BankAccountService();
        BankAccount account = service.createAccount("ACCT-4001", 100.0);

        assertThrows(IllegalStateException.class, () -> service.withdraw(account, 101.0));
        assertEquals(100.0, account.getBalance());
    }
}
