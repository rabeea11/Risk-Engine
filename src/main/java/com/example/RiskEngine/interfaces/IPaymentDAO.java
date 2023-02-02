package com.example.RiskEngine.interfaces;

import com.example.RiskEngine.model.PaymentDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface IPaymentDAO extends CrudRepository<PaymentDTO,String> {

    }
