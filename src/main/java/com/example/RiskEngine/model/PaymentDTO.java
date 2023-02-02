package com.example.RiskEngine.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.retry.annotation.EnableRetry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.UUID;

@Data
@Entity
public class PaymentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String paymentId;
    private long amount;
    private String currency;
    private String userId;
    private String payeeId;
    private String paymentMethodId;
    private int risk;
}
