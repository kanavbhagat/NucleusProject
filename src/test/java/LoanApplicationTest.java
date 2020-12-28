//import com.nucleus.config.TestConfig;
//import com.nucleus.customer.model.Address;
//import com.nucleus.customer.model.Customer;
//import com.nucleus.loanapplications.model.LoanApplications;
//import com.nucleus.loanapplications.service.LoanApplicationService;
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
//public class LoanApplicationTest {
//    @Autowired
//    LoanApplicationService loanApplicationService;
//
//    @Test
//    public void getAllLoanApplicationsListTest() {
//        assertNotNull(loanApplicationService.getAllLoanApplicationsList());
//    }
//
//    @Test
//    public void getLoanApplicationIdTest() {
//        assertNull(loanApplicationService.getLoanApplicationId(1));
//    }
//
////    @Test
////    public void deleteLoanApplicationIdTest() {
////        assertFalse(loanApplicationService.deleteLoanApplicationId(123456));
////    }
//
//    @Test
//    public void updateLoanApplicationTest(){
//        assertFalse(loanApplicationService.updateLoanApplication(new LoanApplications()));
//    }
//
//
//}
