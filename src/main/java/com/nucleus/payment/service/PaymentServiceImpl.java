package com.nucleus.payment.service;

import com.nucleus.payment.dao.PaymentDAOImpl;
import com.nucleus.payment.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Payment> getAllPayments(){
        return this.paymentDAO.getPaymentsList();
    }

    public boolean deletePayment(int loanID){
        return this.paymentDAO.deletePayment(loanID);
    }

    public Payment getPaymentByLoanID(int loanID){
        return this.paymentDAO.getPaymentById(loanID);
    }
}
