package com.atm.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BankAccountTest {
    @Test
    void chainedConstructorsAndStaticCounter() {
        BankAccount defaultAccount = new BankAccount();
        BankAccount namedAccount = new BankAccount("ACCT-1001");
        BankAccount fundedAccount = new BankAccount("ACCT-1002", 250.0);

        assertEquals("ACCT-1000", defaultAccount.getAccountNumber());
        assertEquals("ACCT-1001", namedAccount.getAccountNumber());
        assertEquals(250.0, fundedAccount.getBalance());
        assertEquals(1001, BankAccount.getNextAccountNumber());
    }

    @Test
    void validatesDepositAndWithdraw() {
        BankAccount account = new BankAccount("ACCT-2000", 100.0);

        assertEquals(150.0, account.deposit(50.0));
        assertEquals(50.0, account.withdraw(100.0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-1.0));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-1.0));
        assertThrows(IllegalStateException.class, () -> account.withdraw(51.0));
    }

    @Test
    void equalityAndHashCodeUseAccountNumber() {
        BankAccount first = new BankAccount("ACCT-3000", 100.0);
        BankAccount second = new BankAccount("ACCT-3000", 200.0);

        assertTrue(first.equals(second));
        assertEquals(first.hashCode(), second.hashCode());
        assertFalse(first.equals(new BankAccount("ACCT-3001", 100.0)));
    }
}
