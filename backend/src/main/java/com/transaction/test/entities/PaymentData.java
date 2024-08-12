package com.transaction.test.entities;

public class PaymentData {
    private boolean cardsPayment;
    private long amount;
    public long getAmount() {
        return amount;
    }
    public boolean isCardsPayment() {
        return cardsPayment;
    }
}
