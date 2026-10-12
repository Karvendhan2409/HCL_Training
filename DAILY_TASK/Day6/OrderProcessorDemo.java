public class OrderProcessorDemo {
    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor();

        processor.processOrder("SKU-100", "3", 5);
        processor.processOrder("SKU-200", "two", 5);
        processor.processOrder("SKU-300", "8", 5);
    }
}

class OrderProcessor {
    public void processOrder(String sku, String quantityText, int availableStock) {
        String status = "FAILED";

        try {
            int quantity = parseQuantity(quantityText);
            reserveStock(sku, quantity, availableStock);
            status = "SUCCESS";
            System.out.printf("ORDER ACCEPTED sku=%s quantity=%d%n", sku, quantity);
        } catch (InvalidQuantityException | InsufficientStockException exception) {
            System.out.printf("ORDER REJECTED sku=%s reason=%s%n", sku, exception.getMessage());
            exception.printStackTrace(System.out);
        } finally {
            System.out.printf("AUDIT sku=%s status=%s%n%n", sku, status);
        }
    }

    private int parseQuantity(String quantityText) {
        final int quantity;
        try {
            quantity = Integer.parseInt(quantityText);
        } catch (NumberFormatException cause) {
            throw new InvalidQuantityException(
                    "Quantity must be a whole number: " + quantityText, cause);
        }

        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be greater than zero.");
        }
        return quantity;
    }

    private void reserveStock(String sku, int quantity, int availableStock)
            throws InsufficientStockException {
        if (quantity > availableStock) {
            IllegalStateException cause = new IllegalStateException(
                    "Inventory rejected reservation: requested " + quantity
                            + ", available " + availableStock);
            throw new InsufficientStockException(
                    "Insufficient stock for " + sku + ": requested " + quantity
                            + ", available " + availableStock,
                    cause);
        }
    }
}

class InsufficientStockException extends Exception {
    public InsufficientStockException(String message, Throwable cause) {
        super(message, cause);
    }
}

class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException(String message) {
        super(message);
    }

    public InvalidQuantityException(String message, Throwable cause) {
        super(message, cause);
    }
}