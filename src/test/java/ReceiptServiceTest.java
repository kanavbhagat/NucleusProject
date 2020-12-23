import com.nucleus.config.TestConfig;
import com.nucleus.receipt.model.Advice;
import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.model.Settlement;
import com.nucleus.receipt.service.ReceiptService;
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
public class ReceiptServiceTest {

    @Autowired
    ReceiptService receiptService;

    @Test
    public void registerReceiptTest(){
        Receipt receipt = new Receipt();
        assertFalse(receiptService.registerReceipt(receipt));
    }

    @Test
    public void ReceiptSearchTest(){
        assertTrue(receiptService.receiptSearch("random", "random", 111, 111).size() == 0);
    }


    @Test
    public void updateReceiptTest(){
        Receipt receipt = new Receipt();
        assertFalse(receiptService.updateReceipt(receipt));
    }

    @Test
    public void getReceiptTest(){
        assertNull(receiptService.getReceipt(777));
    }

    @Test
    public void runBODTest() {
        assertFalse(receiptService.runBOD(new Receipt(), new Advice(), new Settlement()));
    }
}
