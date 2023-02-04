package com.example.RiskEngine.interfaces;

import com.example.RiskEngine.model.Payment;

@FunctionalInterface
public interface RiskCalculator {
    public int calculate();
}
