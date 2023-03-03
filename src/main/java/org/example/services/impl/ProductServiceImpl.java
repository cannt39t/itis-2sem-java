package org.example.services.impl;

import lombok.AllArgsConstructor;
import org.example.model.model1.Product;
import org.example.repository.repository1.OrderRepository;
import org.example.repository.repository1.ProductRepository;
import org.example.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product getByName(String name) {
        return productRepository.getByName(name);
    }

    @Override
    public List<Product> getByNameStartsWith(String name) {
        return productRepository.getByNameStartsWith(name);
    }
}
