//import com.nucleus.config.TestConfig;
//import com.nucleus.customer.model.Address;
//import com.nucleus.customer.model.Customer;
//import com.nucleus.loanapplications.model.LoanApplications;
//import com.nucleus.loanapplications.service.NewLoanApplicationService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import javax.enterprise.inject.New;
//
//import java.time.LocalDate;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = TestConfig.class)
//
//public class NewLoanApplicationTest {
//
//    @Autowired
//    NewLoanApplicationService newLoanApplicationService;
//
//    public LoanApplications initloanapplication(){
//        LoanApplications loanApplications = new LoanApplications();
//        loanApplications.setStatus("");
//        loanApplications.setCustomerCode(new Customer());
//        loanApplications.setLoanApplicationNumber(0);
//        loanApplications.setTenure(1);
//        loanApplications.setRate(1);
//        loanApplications.setAgreementDate(LocalDate.now());
//        loanApplications.setInstallmentDueDate(LocalDate.now());
//        loanApplications.setCreateDate(LocalDate.now());
//        loanApplications.setCreatedBy("");
//        loanApplications.setAuthorizedBy("aa");
//        loanApplications.setLoanAmountRequested(111);
//        return loanApplications;
//    }
//
//    @Test
//    public void addLoanApplicationTest(){
//        assertFalse(newLoanApplicationService.addLoanApplication(initloanapplication()));
//    }
//
//    @Test
//    public void getLoanApplicationListTest() {
//        assertNotNull(newLoanApplicationService.getLoanApplicationList());
//    }
//
//
//}
