package com.example.RiskEngine.service;

import com.example.RiskEngine.interfaces.AllowPayments;
import com.example.RiskEngine.interfaces.IPaymentDAO;
import com.example.RiskEngine.interfaces.IPaymentManagementService;
import com.example.RiskEngine.interfaces.RiskCalculator;
import com.example.RiskEngine.model.Payment;
import com.example.RiskEngine.model.PaymentDTO;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class PaymentManagementService implements IPaymentManagementService {

    @Autowired
    private IPaymentDAO paymentDB;
    @Autowired
    private Gson gson;

    private AtomicInteger allowed = new AtomicInteger(0);
    private AtomicInteger rejected = new AtomicInteger(0);

    @Override
    public void handlePayment(String payment) {
        PaymentDTO paymentDTO=parsePaymentToDTO(payment);
        if(paymentDTO != null)
            paymentDB.save(paymentDTO);
    }

    private PaymentDTO parsePaymentToDTO(String paymentStr) {
        try {
            Payment payment = gson.fromJson(paymentStr, Payment.class);
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setRiskscore(calculateRisk());
            paymentDTO.setPaymentmethodid(payment.getPaymentMethodId());
            paymentDTO.setAmount(payment.getAmount());
            paymentDTO.setUserid(payment.getUserId());
            paymentDTO.setCurrency(payment.getCurrency());
            paymentDTO.setPayeeid(payment.getPayeeId());
            paymentDTO.setAllowed(checkIfAllowed(payment));
            log.info("Insert new Payment to DB: {} ", paymentDTO);
            return paymentDTO;
        } catch (JsonParseException ex) {
            log.error("Message Received from Kafka is Not Valid, Error: {}",ex.getMessage());
            return null;
        }
    }
    //This method Calculates Risk for Each Payment
    private int calculateRisk() {
        RiskCalculator result = () -> (int)(Math.random()*1000);
        return result.calculate();
    }

    // Method to Check if a specific Payment is Accepted / Rejected,
    // We will Accept 70% of the coming Payments, and Reject 30%.
    private boolean checkIfAllowed(Payment payment){
        AllowPayments allowPayments = (payment1 -> {
            if(allowed.get()<7){
                log.info("Payment Accepted : {}",payment);
                allowed.incrementAndGet();
                return true;
            }else{
                rejected.incrementAndGet();
                if(rejected.get() >=3){
                    log.info("Payment Rejected : {}",payment);
                    allowed.set(0);
                    return false;
                }
            }
            return false;
        });
        return allowPayments.allowPayment(payment);
    }
}
