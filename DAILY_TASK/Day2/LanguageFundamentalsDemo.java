public final class LanguageFundamentalsDemo {
    private LanguageFundamentalsDemo() {
    }

    public static void main(String[] args) {
        int total = 0;
        int[] oneDimensional = { 10, 20, 30 };
        int[][] twoDimensional = {
                { 1, 2 },
                { 3, 4 }
        };

        final int WEEKLY_SLABS = 7;
        final double AVG_VALUE = 15.5;
        long overflowSafeTotal = 0L;

        for (int value : oneDimensional) {
            total += value;
            overflowSafeTotal += value;
        }

        int widenedValue = 90;
        byte narrowedValue = (byte) widenedValue;
        double precisionValue = 0.1 + 0.2;

        System.out.println("1-D array total: " + total);
        System.out.println("2-D array: " + twoDimensional[1][1]);
        System.out.println("Final constant: " + WEEKLY_SLABS);
        System.out.println("Widening cast value: " + widenedValue);
        System.out.println("Narrowing cast value: " + narrowedValue);
        System.out.println("Overflow-safe total: " + overflowSafeTotal);
        System.out.println("Floating-point precision: " + precisionValue);
        System.out.println("Average: " + AVG_VALUE);
    }
}
