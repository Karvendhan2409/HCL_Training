import java.util.Objects;

public class PaymentDemo {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        Payment[] payments = {
            new CardPayment(1250.00, "**** 4242"),
            new UpiPayment(499.00, "learner@example.com"),
            new CashPayment(120.00)
        };

        processor.pay(payments[0]);
        processor.pay(payments[1], "UPI-REF-2048");
        processor.pay(payments[2], "CASH-REF-2049");

        ((Refundable) payments[0]).refund(250.00);
        ((Refundable) payments[1]).refund(99.00);
    }
}

abstract class Payment {
    private final double amount;

    protected Payment(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero.");
        }
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public abstract void processPayment(String reference);
}

interface Refundable {
    void refund(double amount);
}

class CardPayment extends Payment implements Refundable {
    private final String maskedCardNumber;

    CardPayment(double amount, String maskedCardNumber) {
        super(amount);
        this.maskedCardNumber = Objects.requireNonNull(maskedCardNumber, "Card number is required.");
    }

    @Override
    public void processPayment(String reference) {
        System.out.printf("Card payment of $%.2f using %s (reference: %s)%n",
                getAmount(), maskedCardNumber, reference);
    }

    @Override
    public void refund(double amount) {
        validateRefund(amount);
        System.out.printf("Card refund of $%.2f initiated.%n", amount);
    }

    private void validateRefund(double amount) {
        if (!Double.isFinite(amount) || amount <= 0 || amount > getAmount()) {
            throw new IllegalArgumentException("Refund must be positive and cannot exceed the payment amount.");
        }
    }
}

class UpiPayment extends Payment implements Refundable {
    private final String upiId;

    UpiPayment(double amount, String upiId) {
        super(amount);
        this.upiId = Objects.requireNonNull(upiId, "UPI ID is required.");
    }

    @Override
    public void processPayment(String reference) {
        System.out.printf("UPI payment of $%.2f from %s (reference: %s)%n",
                getAmount(), upiId, reference);
    }

    @Override
    public void refund(double amount) {
        if (!Double.isFinite(amount) || amount <= 0 || amount > getAmount()) {
            throw new IllegalArgumentException("Refund must be positive and cannot exceed the payment amount.");
        }
        System.out.printf("UPI refund of $%.2f initiated.%n", amount);
    }
}

class CashPayment extends Payment {
    CashPayment(double amount) {
        super(amount);
    }

    @Override
    public void processPayment(String reference) {
        System.out.printf("Cash payment of $%.2f received (reference: %s)%n", getAmount(), reference);
    }
}

class PaymentProcessor {
    private int nextReference = 1;

    public void pay(Payment payment) {
        pay(payment, "PAY-" + nextReference++);
    }

    public void pay(Payment payment, String reference) {
        Objects.requireNonNull(payment, "Payment is required.");
        if (reference == null || reference.trim().isEmpty()) {
            throw new IllegalArgumentException("Payment reference is required.");
        }
        payment.processPayment(reference.trim());
    }
}
