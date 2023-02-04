package com.example.RiskEngine.service;

import com.example.RiskEngine.interfaces.AllowPayments;
import com.example.RiskEngine.interfaces.IPaymentDAO;
import com.example.RiskEngine.interfaces.IPaymentManagementService;
import com.example.RiskEngine.interfaces.RiskCalculator;
import com.example.RiskEngine.model.Payment;
import com.example.RiskEngine.model.PaymentDTO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
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
        paymentDB.save(paymentDTO);
    }

    private PaymentDTO parsePaymentToDTO(String paymentStr) {
        Payment payment = gson.fromJson(paymentStr,Payment.class);
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setRiskscore(calculateRisk());
//        paymentDTO.setPaymentid(UUID.randomUUID().toString());
        paymentDTO.setPaymentmethodid(payment.getPaymentMethodId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setUserid(payment.getUserId());
        paymentDTO.setCurrency(payment.getCurrency());
        paymentDTO.setPayeeid(payment.getPayeeId());
        paymentDTO.setAllowed(checkIfAllowed(payment));
        log.info("Insert new Payment to DB: {} " , paymentDTO);
        return paymentDTO;
    }

    private int calculateRisk() {
        RiskCalculator result = () -> (int)(Math.random()*1000);
        return result.calculate();
    }

    private boolean checkIfAllowed(Payment payment){
        log.info("Rejected : {}",rejected.get());
        log.info("Allowed : {}",allowed.get());

        AllowPayments allowPayments = (payment1 -> {
            if(allowed.get()<7){
                allowed.incrementAndGet();
                return true;
            }else{
                rejected.incrementAndGet();
                if(rejected.get() >=3){
                    allowed.set(0);
                    return false;
                }
            }
            return false;
        });
        return allowPayments.allowPayment(payment);
    }
}
