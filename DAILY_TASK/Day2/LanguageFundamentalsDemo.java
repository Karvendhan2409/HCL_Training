package Day2;

public final class LanguageFundamentalsDemo {
    private LanguageFundamentalsDemo() {
    }

    public static void main(String[] args) {
        boolean isActive = true;
        byte smallestByte = -128;
        short smallestShort = -32_768;
        int total = 0;
        long overflowSafeTotal = 0L;
        float floatValue = 3.5f;
        double doubleValue = 0.1 + 0.2;
        char grade = 'A';

        int[] oneDimensional = { 10, 20, 30 };
        int[][] twoDimensional = {
                { 1, 2 },
                { 3, 4 }
        };

        final int WEEKLY_SLABS = 7;
        final double AVG_VALUE = 15.5;

        for (int value : oneDimensional) {
            total += value;
            overflowSafeTotal += value;
        }

        int widenedValue = 90;
        byte narrowedValue = (byte) widenedValue;
        int expressionResult = 10 + 20 * 2;

        System.out.println("Primitive boolean: " + isActive);
        System.out.println("Primitive byte range: " + smallestByte + " to " + Byte.MAX_VALUE);
        System.out.println("Primitive short range: " + smallestShort + " to " + Short.MAX_VALUE);
        System.out.println("Primitive int total: " + total);
        System.out.println("Primitive long overflow-safe total: " + overflowSafeTotal);
        System.out.println("Primitive float: " + floatValue);
        System.out.println("Primitive double precision: " + doubleValue);
        System.out.println("Primitive char grade: " + grade);
        System.out.println("1-D array total: " + total);
        System.out.println("2-D array: " + twoDimensional[1][1]);
        System.out.println("Final constant: " + WEEKLY_SLABS);
        System.out.println("Operator precedence result: " + expressionResult);
        System.out.println("Widening cast value: " + widenedValue);
        System.out.println("Narrowing cast value: " + narrowedValue);
        System.out.println("Average: " + AVG_VALUE);
    }
}
