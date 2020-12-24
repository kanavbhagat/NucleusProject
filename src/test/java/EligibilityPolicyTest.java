import com.nucleus.config.TestConfig;
import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.eligibilitypolicy.service.EligibilityPolicyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestConfig.class)
public class EligibilityPolicyTest {

    @Autowired
    EligibilityPolicyService eligibilityPolicyService;



    @Test
    public void insertEligibilityPolicyTest() {
        assertFalse(eligibilityPolicyService.insertEligibilityPolicy(new EligibilityPolicy()));
    }

    @Test
    public void getOneEligibilityPolicyTest() {
        assertNull(eligibilityPolicyService.getOneEligibilityPolicy(""));
    }

    @Test
    public void updateStatusTest() {
        assertFalse(eligibilityPolicyService.updateStatus("", "", ""));
    }

    @Test
    public void updateEligibilityPolicyTest() {
        assertFalse(eligibilityPolicyService.updateEligibilityPolicy(new EligibilityPolicy()));
    }

    @Test
    public void deleteEligibilityPolicyTest() {
        assertFalse(eligibilityPolicyService.deleteEligibilityPolicy(""));
    }
}
