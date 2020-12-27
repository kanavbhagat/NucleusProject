import com.nucleus.config.TestConfig;
import com.nucleus.customerservice.customerdetails.service.customerdetailsservice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

import static org.junit.Assert.*;
import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestConfig.class)

public class CustomerDetailsTest {
    @Autowired
    customerdetailsservice customerDetailsService;

    @Test
    public void getCustomerDetailsTest (){
        assertNull(customerDetailsService.getCustomerDetails(""));
    }

}
