package com.training.payment.model;

import java.math.BigDecimal;

public class UpiPayment extends Payment implements Refundable {
    private final String upiId;

    public UpiPayment(BigDecimal amount, String upiId) {
        super(amount);
        if (upiId == null || !upiId.matches("[^\\s@]+@[^\\s@]+")) {
            throw new IllegalArgumentException("A valid UPI ID is required.");
        }
        this.upiId = upiId;
    }

    @Override
    public String pay() {
        markPaid();
        return "UPI payment of " + format(getAmount()) + " sent from " + upiId + ".";
    }

    @Override
    public BigDecimal refund() {
        return completeRefund();
    }
}
