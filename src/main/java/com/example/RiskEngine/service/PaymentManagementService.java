package com.example.RiskEngine.service;

import com.example.RiskEngine.interfaces.IPaymentDAO;
import com.example.RiskEngine.interfaces.IPaymentManagementService;
import com.example.RiskEngine.model.Payment;
import com.example.RiskEngine.model.PaymentDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentManagementService implements IPaymentManagementService {

    @Autowired
    IPaymentDAO paymentDB;
    @Autowired
    Gson gson;

    @Override
    public void handlePayment(String payment) {
        PaymentDTO paymentDTO=parsePaymentToDTO(payment);
        paymentDB.save(paymentDTO);
    }

    private PaymentDTO parsePaymentToDTO(String paymentStr) {
        Payment payment = gson.fromJson(paymentStr,Payment.class);
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setRisk(calculateRisk(payment));
        paymentDTO.setPaymentId(UUID.randomUUID().toString());
        paymentDTO.setPaymentMethodId(payment.getPaymentMethodId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setCurrency(payment.getCurrency());
        paymentDTO.setPayeeId(payment.getPayeeId());

        return paymentDTO;
    }

    private int calculateRisk(Payment payment) {
        return (int)(Math.random()*1000);
    }
}
