import com.nucleus.config.TestConfig;
import com.nucleus.customer.model.Address;
import com.nucleus.customer.model.Customer;
import com.nucleus.customer.service.AddressService;
import org.hibernate.HibernateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.validation.ConstraintViolationException;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestConfig.class)
public class AddressServiceTest {
    @Autowired
    AddressService addressService;

    public Address initAddress(){
        Address address = new Address();
        address.setAddressId(1234567);
        address.setCustomerCode(new Customer());
        address.setHouseNo("1");
        address.setCity("New Delhi");
        address.setState("Punjab");
        address.setCountry("India");
        address.setPinCode(143001);
        return address;
    }

    @Test
    public void insertAddressTest(){
        assertFalse(addressService.insertAddress(initAddress()));
    }
}
