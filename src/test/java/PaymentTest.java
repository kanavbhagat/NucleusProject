import com.nucleus.config.TestConfig;
import com.nucleus.payment.model.Payment;
import com.nucleus.payment.service.PaymentServiceImpl;
import org.hibernate.HibernateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.validation.ConstraintViolationException;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestConfig.class)
public class PaymentTest {

    @Autowired
    PaymentServiceImpl paymentService;

    public Payment initPayment(){
        Payment payment = new Payment();
        payment.setLoanApplicationNumber(1234567);
        payment.setCustomerCode("Cust101");
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentAmount(600000);
        payment.setPayoutBankAccount("1234567890");
        payment.setPaymentChannel("Draft");
        payment.setRemarks("JUnit created test");
        return payment;
    }

    @Test
    public void insertEmptyPaymentTest(){
        assertFalse(paymentService.insertPayment(new Payment()));
    }


    @Test
    public void approveEmptyRejectPaymentTest(){
        assertFalse(paymentService.approveRejectPayment(0, "approve", "checker_1"));
    }


    @Test
    public void updateEmptyPaymentTest(){
        assertFalse(paymentService.updatePayment(new Payment()));
    }

    @Test
    public void deleteEmptyPaymentTest(){
        assertFalse(paymentService.deletePayment(0));
    }

//    @Test
//    public void insertPaymentTest(){
//        assertTrue(paymentService.insertPayment(initPayment()));
//    }
//
//    @Test
//    public void updatePaymentTest(){
//        Payment payment = initPayment();
//        payment.setRemarks("Changed by Junit");
//        assertTrue(paymentService.updatePayment(payment));
//    }

//    @Test
//    public void deletePaymentTest(){
//        assertTrue(paymentService.deletePayment(1234567));
//    }

}