//package com.example.RiskEngine.service;
//
//import com.example.RiskEngine.interfaces.IPaymentDAO;
//import com.example.RiskEngine.model.Payment;
//import com.google.gson.Gson;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.internal.matchers.Any;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//public class PaymentManagementServiceTest {
//
//    @InjectMocks
//    PaymentManagementService paymentService = new PaymentManagementService();
//    @Mock
//    private IPaymentDAO paymentDB;
//
//    @Mock
//    private Gson gson;
////    Gson mockGson = mock
//
//
//    @Test
//    public void mockingFinalClass() {
//        Gson gson = mock(Gson.class);
//        Payment payment = new Payment(12,"ILS","e8af92bd-1910-421e-8de0-cb3dcf9bf44d","4c3e304e-ce79-4f53-bb26-4e198e6c780a","8e28af1b-a3a0-43a9-96cc-57d66dd22494");
//
//        when(gson.fromJson(,Payment.class)).thenReturn(payment);
//    }
//
//    @Test
//    public void testHandlePayment(){
//        Payment payment = new Payment(12,"ILS","e8af92bd-1910-421e-8de0-cb3dcf9bf44d","4c3e304e-ce79-4f53-bb26-4e198e6c780a","8e28af1b-a3a0-43a9-96cc-57d66dd22494");
//        paymentService.handlePayment(payment.toString());
//    }
//}