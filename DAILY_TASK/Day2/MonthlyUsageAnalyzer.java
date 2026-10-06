public final class MonthlyUsageAnalyzer {
    private MonthlyUsageAnalyzer() {
    }

    public static long calculateTotal(int[] usage) {
        long total = 0L;
        for (int value : usage) {
            total += value;
        }
        return total;
    }

    public static double calculateAverage(int[] usage) {
        if (usage.length == 0) {
            throw new IllegalArgumentException("Usage data cannot be empty");
        }
        return calculateTotal(usage) / (double) usage.length;
    }

    public static int calculateMaximum(int[] usage) {
        if (usage.length == 0) {
            throw new IllegalArgumentException("Usage data cannot be empty");
        }
        int maximum = usage[0];
        for (int value : usage) {
            maximum = Math.max(maximum, value);
        }
        return maximum;
    }

    public static int calculateMinimum(int[] usage) {
        if (usage.length == 0) {
            throw new IllegalArgumentException("Usage data cannot be empty");
        }
        int minimum = usage[0];
        for (int value : usage) {
            minimum = Math.min(minimum, value);
        }
        return minimum;
    }

    public static char calculateGrade(double average) {
        return average >= Constants.GRADE_A_THRESHOLD
                ? 'A'
                : average >= Constants.GRADE_B_THRESHOLD
                        ? 'B'
                        : 'C';
    }

    public static long[] calculateHouseTotals(int[][] houseUsage) {
        long[] totals = new long[houseUsage.length];
        for (int houseIndex = 0; houseIndex < houseUsage.length; houseIndex++) {
            long total = 0L;
            for (int usage : houseUsage[houseIndex]) {
                total += usage;
            }
            totals[houseIndex] = total;
        }
        return totals;
    }
}
