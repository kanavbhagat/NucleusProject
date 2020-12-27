import com.nucleus.config.TestConfig;
import com.nucleus.customerservice.loandisbursal.service.LoanDisbursalService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestConfig.class)
public class LoanDisbursalTest {

    @Autowired
    LoanDisbursalService loanDisbursalService;

    @Test
    public void getLoanDetailsTest(){
        assertNull(loanDisbursalService.getLoanDetails(0));
    }

    @Test
    public void getCustomerLoanDetailsTest(){
        Assert.assertEquals(loanDisbursalService.getCustomerLoanDetails("C"),null);
    }
}