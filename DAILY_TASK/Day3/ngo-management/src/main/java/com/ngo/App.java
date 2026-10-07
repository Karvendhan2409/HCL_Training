package com.ngo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Donation> donations = new ArrayList<Donation>();
    private final List<String> volunteers = new ArrayList<String>();

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addDonation();
                    break;
                case "2":
                    addVolunteer();
                    break;
                case "3":
                    showSummary();
                    break;
                case "4":
                    running = false;
                    System.out.println("Thank you for supporting the NGO.");
                    break;
                default:
                    System.out.println("Please choose an option from 1 to 4.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== NGO Donation and Volunteer Management ===");
        System.out.println("1. Record a donation");
        System.out.println("2. Register a volunteer");
        System.out.println("3. View summary");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private void addDonation() {
        String donorName = readRequiredText("Donor name: ");
        System.out.print("Donation amount: ");
        String amountInput = scanner.nextLine().trim();

        try {
            double amount = Double.parseDouble(amountInput);
            if (amount <= 0) {
                System.out.println("Donation amount must be greater than zero.");
                return;
            }

            donations.add(new Donation(donorName, amount));
            System.out.printf("Donation of $%.2f recorded for %s.%n", amount, donorName);
        } catch (NumberFormatException exception) {
            System.out.println("Please enter a valid numeric amount.");
        }
    }

    private void addVolunteer() {
        String volunteerName = readRequiredText("Volunteer name: ");
        volunteers.add(volunteerName);
        System.out.println("Volunteer registered: " + volunteerName);
    }

    private String readRequiredText(String prompt) {
        String value;
        do {
            System.out.print(prompt);
            value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println("This value cannot be empty.");
            }
        } while (value.isEmpty());
        return value;
    }

    private void showSummary() {
        double totalDonations = 0;
        for (Donation donation : donations) {
            totalDonations += donation.amount;
        }

        System.out.println("\n=== NGO Summary ===");
        System.out.println("Donation records: " + donations.size());
        System.out.printf("Total donations: $%.2f%n", totalDonations);
        System.out.println("Registered volunteers: " + volunteers.size());

        if (!volunteers.isEmpty()) {
            System.out.println("Volunteer names: " + String.join(", ", volunteers));
        }
    }

    private static class Donation {
        private final String donorName;
        private final double amount;

        private Donation(String donorName, double amount) {
            this.donorName = donorName;
            this.amount = amount;
        }
    }
}