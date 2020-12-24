import com.nucleus.config.TestConfig;
import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
import com.nucleus.eligibiltyparameter.service.EligibilityParameterService;
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
public class EligibilityParameterTest {

    @Autowired
    EligibilityParameterService eligibilityParameterService;



    @Test
    public void insertEligibilityParameterTest() {
        assertFalse(eligibilityParameterService.insertParameter(new EligibilityParameter()));
    }

    @Test
    public void getOneEligibilityParameterTest() {
        assertNull(eligibilityParameterService.getOneEligibilityParameter(""));
    }

    @Test
    public void updateStatusTest() {
        assertFalse(eligibilityParameterService.updateStatus("", "", ""));
    }

    @Test
    public void editEligibilityParameterTest() {
        assertFalse(eligibilityParameterService.editParameter(new EligibilityParameter()));
    }

    @Test
    public void deleteEligibilityParameterTest() {
        assertFalse(eligibilityParameterService.deleteEligibilityParameter(""));
    }
}