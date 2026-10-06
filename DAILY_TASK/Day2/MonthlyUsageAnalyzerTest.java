public class MonthlyUsageAnalyzerTest {
    public static void main(String[] args) {
        int[] monthlyUsage = { 120, 135, 150, 145, 160, 175, 190, 180, 170, 165, 155, 145 };
        int[][] houseUsage = {
                { 100, 110, 120 },
                { 130, 140, 150 },
                { 160, 170, 180 }
        };

        long total = MonthlyUsageAnalyzer.calculateTotal(monthlyUsage);
        double average = MonthlyUsageAnalyzer.calculateAverage(monthlyUsage);
        int maximum = MonthlyUsageAnalyzer.calculateMaximum(monthlyUsage);
        int minimum = MonthlyUsageAnalyzer.calculateMinimum(monthlyUsage);
        char grade = MonthlyUsageAnalyzer.calculateGrade(average);
        long[] houseTotals = MonthlyUsageAnalyzer.calculateHouseTotals(houseUsage);

        assertEquals(1890L, total, "monthly total");
        assertEquals(157.5, average, "monthly average");
        assertEquals(190, maximum, "monthly maximum");
        assertEquals(120, minimum, "monthly minimum");
        assertEquals('A', grade, "performance grade");
        assertEquals(330L, houseTotals[0], "first house total");
        assertEquals(420L, houseTotals[1], "second house total");
        assertEquals(510L, houseTotals[2], "third house total");

        System.out.println("MonthlyUsageAnalyzerTest: 7 checks passed");
    }

    private static void assertEquals(long expected, long actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + ": expected " + expected + " but was " + actual);
        }
    }

    private static void assertEquals(double expected, double actual, String message) {
        if (Double.compare(expected, actual) != 0) {
            throw new AssertionError(message + ": expected " + expected + " but was " + actual);
        }
    }

    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + ": expected " + expected + " but was " + actual);
        }
    }

    private static void assertEquals(char expected, char actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + ": expected " + expected + " but was " + actual);
        }
    }
}
