package com.nucleus.payment.service;

import com.nucleus.payment.dao.PaymentDAOImpl;
import com.nucleus.payment.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentDAOImpl paymentDAO;

    public PaymentDAOImpl getPaymentDAO(){
        return paymentDAO;
    }

    public boolean insertPayment(Payment payment){
        System.out.println("Inside Payment Service");
        return this.paymentDAO.insertPayment(payment);
    }
}
