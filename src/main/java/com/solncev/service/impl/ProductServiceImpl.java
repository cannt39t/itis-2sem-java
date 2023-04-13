package com.solncev.service.impl;

import com.solncev.model.Product;
import com.solncev.repository.ProductRepository;
import com.solncev.service.ProductService;
import lombok.AllArgsConstructor;

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

    public List<Product> findAll() { return productRepository.findAll(); }

    @Override
    public List<Product> getByNameStartsWith(String name) {
        return productRepository.getByNameStartsWith(name);
    }
}
