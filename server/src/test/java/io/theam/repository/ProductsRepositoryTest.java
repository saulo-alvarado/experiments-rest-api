package io.theam.repository;

import io.theam.model.Customer;
import io.theam.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductsRepositoryTest {

    @Autowired
    private ProductRepository customerRepository;

    @Test
    public void repositorySavesProduct() {
        Product product = new Product();
        product.setRef("JUGUETO");
        product.setName("PLAYMOBIL");
        product.setCommonPrice(10.01);

        Product result = customerRepository.save(product);

        assertEquals(result.getRef(), "JUGUETO");
        assertEquals(result.getName(), "PLAYMOBIL");
        assertEquals(result.getCommonPrice(), new Double(10.01));
    }


}
