package com.training.payment.model;

import java.math.BigDecimal;

public class CardPayment extends Payment implements Refundable {
    private final String lastFourDigits;

    public CardPayment(BigDecimal amount, String lastFourDigits) {
        super(amount);
        if (lastFourDigits == null || !lastFourDigits.matches("\\d{4}")) {
            throw new IllegalArgumentException("Card number must be represented by its last four digits.");
        }
        this.lastFourDigits = lastFourDigits;
    }

    @Override
    public String pay() {
        markPaid();
        return "Card payment of " + format(getAmount())
                + " approved for card ending " + lastFourDigits + ".";
    }

    @Override
    public BigDecimal refund() {
        return completeRefund();
    }
}
