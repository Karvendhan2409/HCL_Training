package com.training.payment.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class PaymentHierarchyTest {
    @Test
    void overloadedPayProcessesPaymentWithReference() {
        CardPayment payment = new CardPayment(new BigDecimal("49.99"), "4242");

        String receipt = payment.pay("ORDER-1001");

        assertTrue(receipt.contains("$49.99"));
        assertTrue(receipt.contains("ORDER-1001"));
        assertThrows(IllegalStateException.class, payment::pay);
    }

    @Test
    void invalidReferenceDoesNotProcessPayment() {
        UpiPayment payment = new UpiPayment(new BigDecimal("24.50"), "student@bank");

        assertThrows(IllegalArgumentException.class, () -> payment.pay(" "));
        assertTrue(payment.pay().contains("student@bank"));
    }

    @Test
    void refundablePaymentCanBeRefundedOnlyOnceAfterPayment() {
        UpiPayment payment = new UpiPayment(new BigDecimal("24.50"), "student@bank");

        assertThrows(IllegalStateException.class, payment::refund);
        payment.pay();
        assertEquals(new BigDecimal("24.50"), payment.refund());
        assertThrows(IllegalStateException.class, payment::refund);
    }

    @Test
    void cashPaymentCalculatesChangeAndCanBeRefunded() {
        CashPayment payment = new CashPayment(new BigDecimal("10.00"), new BigDecimal("20.00"));

        assertEquals(new BigDecimal("10.00"), payment.getChange());
        assertTrue(payment.pay().contains("Change: $10.00"));
        assertEquals(new BigDecimal("10.00"), payment.refund());
    }

    @Test
    void validatesAmountsAndPaymentDetails() {
        assertThrows(IllegalArgumentException.class,
                () -> new CardPayment(BigDecimal.ZERO, "4242"));
        assertThrows(IllegalArgumentException.class,
            () -> new CardPayment(new BigDecimal("0.001"), "4242"));
        assertThrows(IllegalArgumentException.class,
                () -> new CardPayment(new BigDecimal("1.00"), "123"));
        assertThrows(IllegalArgumentException.class,
                () -> new UpiPayment(new BigDecimal("1.00"), "invalid-upi"));
        assertThrows(IllegalArgumentException.class,
                () -> new CashPayment(new BigDecimal("10.00"), new BigDecimal("9.00")));
    }
}
