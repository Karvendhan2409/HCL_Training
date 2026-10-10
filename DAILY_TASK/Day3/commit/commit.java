
package day3.commit;

import java.util.Scanner;

public class commit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int balance = 5000;
        int choice;

        do {
            System.out.println("\n--- ATM Simulator ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: Rs." + balance);
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ");
                    int deposit = sc.nextInt();

                    if (deposit > 0) {
                        balance += deposit;
                        System.out.println("Deposit successful!");
                    } else {
                        System.out.println("Invalid amount!");
                    }
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    int withdraw = sc.nextInt();

                    if (withdraw > 0 && withdraw <= balance) {
                        balance -= withdraw;
                        System.out.println("Withdrawal successful!");
                    } else {
                        System.out.println("Invalid amount or insufficient balance!");
                    }
                    break;

                case 4:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);

        sc.close();
    }
}
