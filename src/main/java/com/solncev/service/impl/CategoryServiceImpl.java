package com.solncev.service.impl;

import com.solncev.model.Category;
import com.solncev.model.Product;
import com.solncev.repository.CategoryRepository;
import com.solncev.service.CategoryService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getByIdNotNull(Integer id) {
        return null;
    }

    @Override
    public Category findByProductsContains(Product product) {
        return categoryRepository.findByProductsContains(product);
    }
}
