//package java;
//import com.nucleus.receipt.model.Receipt;
//import com.nucleus.receipt.service.ReceiptService;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class ReceiptServiceTest {
//
//    ReceiptService receiptService = new ReceiptService();
//
//
//    @BeforeClass
//    public void init() {
//        System.out.println("Receipt Service Test Starts");
//    }
//
//    @Test
//    public void registerEmptyReceiptTest()
//    {
//        Receipt receipt = new Receipt();
//
//        //test
//        assertFalse(receiptService.registerReceipt(receipt));
//
//    }
//
//    @Test
//    public void randomReceiptSearchTest()
//    {
//        assertTrue(receiptService.receiptSearch("random", "random", 111, 111).size() == 0);
//    }
//}
