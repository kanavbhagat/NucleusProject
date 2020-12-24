import com.nucleus.config.TestConfig;
import com.nucleus.product.model.Product;
import com.nucleus.product.service.ProductService;
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
public class ProductServiceTest {

    @Autowired
    ProductService productService;


    @Test
    public void createNewProductTest(){
        assertFalse(productService.createNewProduct(new Product()));
    }

    @Test
    public void getProductByIdTest(){
        assertNull(productService.getProductById("random"));
    }

    @Test
    public void updateProductTest(){
        assertNull(productService.updateProduct(new Product()));
    }

    @Test
    public void deleteProductTest(){
        assertFalse(productService.deleteProduct("random"));
    }

}

