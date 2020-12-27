//import com.nucleus.config.TestConfig;
//import com.nucleus.customer.model.Customer;
//import com.nucleus.customer.service.NewCustomerService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import javax.enterprise.inject.New;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNull;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = TestConfig.class)
//
//public class NewCustomerServiceTest {
//
//    @Autowired
//    NewCustomerService newCustomerService;
//
//
//    @Test
//    public void createNewCustomerTest() {
//        assertFalse(newCustomerService.createNewCustomer(new Customer()));
//    }
//
//    @Test
//    public void getCustomerTest(){
//        assertNull(newCustomerService.getCustomer("random"));
//    }
//}
