package com.atm;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class AtmSimulatorTest {
    @Test
    void depositsMoneyAndPrintsTheUpdatedBalance() {
        String input = "1234\n1\n100\n4\n";
        String output = run(input);

        assertTrue(output.contains("New balance: $1100.00"));
        assertTrue(output.contains("Deposit completed"));
    }

    @Test
    void stopsAfterThreeIncorrectPinAttempts() {
        String input = "0000\n0000\n0000\n";
        String output = run(input);

        assertTrue(output.contains("Incorrect PIN. Attempt 1 of 3"));
        assertTrue(output.contains("Incorrect PIN. Attempt 2 of 3"));
        assertTrue(output.contains("Incorrect PIN. Attempt 3 of 3"));
        assertTrue(output.indexOf("=== ATM Menu ===") < 0);
    }

    @Test
    void printsMiniStatementFromTransactions() {
        String input = "1234\n1\n250\n3\n4\n";
        String output = run(input);

        assertTrue(output.contains("Mini Statement"));
        assertTrue(output.contains("Deposit: $250.00"));
    }

    private String run(String input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        AtmSimulator simulator = new AtmSimulator(
                new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))),
                new PrintStream(output));
        simulator.run();
        return output.toString();
    }
}
