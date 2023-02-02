package com.example.RiskEngine.kafka;

import com.example.RiskEngine.model.Payment;
import com.example.RiskEngine.model.PaymentDTO;
import com.example.RiskEngine.interfaces.IPaymentDAO;
import com.example.RiskEngine.service.PaymentManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    @Autowired
    PaymentManagementService paymentManagementService;

    @KafkaListener(topics = "${spring.kafka.topic.name}")
    public void consume(String payment) {
        log.info("message consumed {}", payment);
        paymentManagementService.handlePayment(payment);


    }
}
