package com.training.payment.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CashPayment extends Payment implements Refundable {
    private final BigDecimal tenderedAmount;

    public CashPayment(BigDecimal amount, BigDecimal tenderedAmount) {
        super(amount);
        if (tenderedAmount == null) {
            throw new IllegalArgumentException("Cash tendered is required.");
        }
        this.tenderedAmount = tenderedAmount.setScale(2, RoundingMode.HALF_UP);
        if (this.tenderedAmount.compareTo(getAmount()) < 0) {
            throw new IllegalArgumentException("Cash tendered cannot be less than the payment amount.");
        }
    }

    public BigDecimal getChange() {
        return tenderedAmount.subtract(getAmount());
    }

    @Override
    public String pay() {
        markPaid();
        return "Cash payment of " + format(getAmount()) + " accepted. Change: " + format(getChange()) + ".";
    }

    @Override
    public BigDecimal refund() {
        return completeRefund();
    }
}
