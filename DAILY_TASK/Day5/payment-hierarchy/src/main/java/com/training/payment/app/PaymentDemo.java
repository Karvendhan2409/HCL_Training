package com.training.payment.app;

import java.math.BigDecimal;

import com.training.payment.model.CardPayment;
import com.training.payment.model.CashPayment;
import com.training.payment.model.Payment;
import com.training.payment.model.Refundable;
import com.training.payment.model.UpiPayment;

public class PaymentDemo {
    public static void main(String[] args) {
        System.out.println("Payment options: Card, UPI, Cash");

        Payment card = new CardPayment(new BigDecimal("49.99"), "4242");
        Payment upi = new UpiPayment(new BigDecimal("24.50"), "student@bank");
        Payment cash = new CashPayment(new BigDecimal("10.00"), new BigDecimal("20.00"));

        System.out.println(card.pay("ORDER-1001"));
        System.out.println(upi.pay());
        System.out.println(cash.pay());

        Refundable refundablePayment = (Refundable) upi;
        System.out.println("Refund issued: $" + refundablePayment.refund().toPlainString());
    }
}
