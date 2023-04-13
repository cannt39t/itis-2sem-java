package com.solncev.service;


import com.solncev.Application;
import com.solncev.model.Product;
import com.solncev.repository.ClientRepository;
import com.solncev.repository.ProductRepository;
import com.solncev.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(ProductServiceImpl.class)
@ContextConfiguration(classes = Application.class)
public class ProductServiceUnitTest {

    @Autowired
    private ProductServiceImpl productService;
    @MockBean
    private ProductRepository productRepository;

    @Test
    public void contextLoads() throws Exception {
        assertThat(productService).isNotNull();
    }

    @Test
    public void testFindAllProducts_thenReturningListOfProducts() throws Exception {
        when(productRepository.findAll()).thenReturn(List.of());
        List<Product> expectedClientList = this.productService.findAll();

        assertEquals(List.of(), expectedClientList);
        verify(this.productRepository, times(1)).findAll();
    }

    @Test
    public void testFindProductByName_thenReturningProduct() {
        String productName = "Sneakers";
        productService.getByName(productName);
        verify(this.productRepository, times(1)).getByName(productName);
    }

    @Test
    public void testDeleteClientById() throws Exception {
        String productName = "Sneakers";
        productService.getByNameStartsWith(productName);
        verify(this.productRepository, times(1)).getByNameStartsWith(productName);
    }

}
