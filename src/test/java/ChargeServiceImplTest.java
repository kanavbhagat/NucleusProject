//import com.nucleus.charge.model.NewCharge;
//import com.nucleus.charge.service.ChargeServiceImpl;
//import com.nucleus.config.AppConfig;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNull;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = AppConfig.class)
//public class ChargeServiceImplTest {
//
//    @Autowired
//    ChargeServiceImpl chargeServiceImpl;
//
//    private NewCharge charge;
//    private String chargeCode,status;
//
//    @Before
//    public void setUp() {
//        charge = new NewCharge();
//        status = "";
//        chargeCode = "";
//        charge.setChargeCode("C100");
//        charge.setChargeCodeName("ChargeA");
//        charge.setTransactionEvent("Processing fees");
//        charge.setChargePaymentType("Receivable");
//        charge.setChargeType("Amount Based");
//    }
//
//    @Test
//    public void insertCharge() {
//        assertFalse(chargeServiceImpl.insertCharge(charge,status));
//    }
//
//    @Test
//    public void getChargeList() {
//        assertFalse(chargeServiceImpl.getChargeList().isEmpty());
//    }
//
//    @Test
//    public void getPendingChargeList() {
//        assertFalse(chargeServiceImpl.getPendingChargeList().isEmpty());
//    }
//
//    @Test
//    public void deleteCharge() {
//        assertFalse(chargeServiceImpl.deleteCharge(chargeCode));
//    }
//
//    @Test
//    public void getOneCharge() {
//        assertNull(chargeServiceImpl.getOneCharge(chargeCode));
//    }
//
//    @Test
//    public void updateStatus() {
//        assertFalse(chargeServiceImpl.updateStatus(chargeCode,status));
//    }
//
//    @Test
//    public void updateCharge() {
//        assertFalse(chargeServiceImpl.updateCharge(charge));
//    }
//}