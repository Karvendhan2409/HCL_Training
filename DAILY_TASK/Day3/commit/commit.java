
package day3.commit;

import java.util.Scanner;

public class commit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;
        int total = 0;
        int[] marks = new int[3];

        do {
            System.out.println("\n--- Student Marks System ---");
            System.out.println("1. Enter Marks");
            System.out.println("2. Display Total");
            System.out.println("3. Display Average");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    total = 0;

                    for (int i = 0; i < 3; i++) {
                        System.out.print("Enter subject " + (i + 1) + " marks: ");
                        marks[i] = sc.nextInt();

                        if (marks[i] < 0 || marks[i] > 100) {
                            System.out.println("Invalid marks! Enter 0 to 100.");
                            i--;
                        } else {
                            total += marks[i];
                        }
                    }
                    System.out.println("Marks saved successfully!");
                    break;

                case 2:
                    System.out.println("Total Marks: " + total);
                    break;

                case 3:
                    System.out.println("Average: " + (total / 3.0));
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
