package com.example.RiskEngine.interfaces;

import com.example.RiskEngine.model.Payment;

@FunctionalInterface
public interface AllowPayments {
    boolean allowPayment(Payment payment);
}
