import com.nucleus.chargepolicy.model.ChargePolicy;
import com.nucleus.chargepolicy.service.ChargePolicyService;
import com.nucleus.config.TestConfig;
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
public class chargePolicyTest {
    @Autowired
    ChargePolicyService chargePolicyService;

    @Test
    public void insertNewChargePolicy() {
        assertEquals(chargePolicyService.insert(null), 3);
    }

    @Test
    public void getChargePolicyTest() {
        assertNull(chargePolicyService.getChargePolicy(""));
    }

    @Test
    public void getChargeCodeNameTest() {
        assertNull(chargePolicyService.getChargeCodeName(""));
    }
    @Test
    public void updateStatusTest() {
        assertFalse(chargePolicyService.updateStatus("","", ""));
    }
    @Test
    public void updateEntryTest() {
        assertFalse(chargePolicyService.updateEntry(new ChargePolicy(), ""));
    }

}
