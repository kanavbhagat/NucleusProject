import com.nucleus.config.TestConfig;
import com.nucleus.loanclosurebod.service.LoanClosureService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.criteria.CriteriaBuilder;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestConfig.class)
public class LoanClosureBodTest {

    @Autowired
    LoanClosureService loanClosureService;

    @Test
    public void loanClosureBodTest(){
        assertTrue(loanClosureService.loanClosureBod()>=0);
    }
}