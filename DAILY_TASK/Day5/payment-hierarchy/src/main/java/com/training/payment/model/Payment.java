package com.training.payment.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Payment {
    private final BigDecimal amount;
    private boolean paid;
    private boolean refunded;

    protected Payment(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero.");
        }
        BigDecimal normalizedAmount = amount.setScale(2, RoundingMode.HALF_UP);
        if (normalizedAmount.signum() <= 0) {
            throw new IllegalArgumentException("Payment amount must be at least one cent.");
        }
        this.amount = normalizedAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public abstract String pay();

    public String pay(String reference) {
        if (reference == null || reference.isBlank()) {
            throw new IllegalArgumentException("Payment reference is required.");
        }
        return pay() + " Reference: " + reference.trim() + ".";
    }

    protected final void markPaid() {
        if (paid) {
            throw new IllegalStateException("This payment has already been processed.");
        }
        paid = true;
    }

    protected final BigDecimal completeRefund() {
        if (!paid) {
            throw new IllegalStateException("A payment must be processed before it can be refunded.");
        }
        if (refunded) {
            throw new IllegalStateException("This payment has already been refunded.");
        }
        refunded = true;
        return amount;
    }

    protected static String format(BigDecimal value) {
        return "$" + value.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}
