
package Day6;

import java.util.Scanner;

public class example {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int a = scan.nextInt();
        int b = scan.nextInt();

        try {
            int c = a / b;
            System.out.println(c);
        } catch (ArithmeticException e) {
            System.out.println("Denominator should not be zero!");
        }

        System.out.println("Program ended successfully!");
        scan.close();
    }
}
