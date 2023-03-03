package org.example.services.impl;

import lombok.AllArgsConstructor;
import org.example.model.model1.Category;
import org.example.model.model1.Product;
import org.example.repository.repository1.CategoryRepository;
import org.example.repository.repository1.ClientRepository;
import org.example.services.CategoryService;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getByIdNotNull(Integer id) {
        return categoryRepository.getByIdNotNull(id);
    }

    @Override
    public Category findByProductsContains(Product product) {
        return categoryRepository.findByProductsContains(product);
    }
}
