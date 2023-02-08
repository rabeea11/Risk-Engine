package com.example.RiskEngine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    public long amount;
    public String currency;
    private String userId = UUID.randomUUID().toString();
    private String payeeId = UUID.randomUUID().toString();
    private String paymentMethodId ;
}
