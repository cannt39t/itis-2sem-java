package org.example.contollers;


import lombok.RequiredArgsConstructor;
import org.example.model.model1.Category;
import org.example.model.model1.Client;
import org.example.model.model1.Order;
import org.example.model.model1.Product;
import org.example.services.impl.CategoryServiceImpl;
import org.example.services.impl.ClientServiceImpl;
import org.example.services.impl.OrderServiceImpl;
import org.example.services.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ClientServiceImpl clientService;
    private final CategoryServiceImpl categoryService;
    private final ProductServiceImpl productService;
    private final OrderServiceImpl orderService;

    @GetMapping("/shop/client/{id}")
    public Client getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    @DeleteMapping("/shop/client/{id}")
    public void deleteUser(@PathVariable Integer id) {
        clientService.deleteByIdNotNull(id);
    }

    @GetMapping("/shop/category/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        return categoryService.getByIdNotNull(id);
    }

    @GetMapping("/shop/category")
    public Category getCategoryByProduct(@Valid @RequestBody Product product) {
        return categoryService.findByProductsContains(product);
    }

    @GetMapping("/shop/orders/{user_id}")
    public List<Order> getOrdersOfUserWithId(@PathVariable Integer user_id) {
        return orderService.getAllByClientId(user_id);
    }

    @GetMapping("/shop/order/{user_id}")
    public Order getOrderById(@PathVariable Integer user_id) {
        return orderService.getOrderById(user_id);
    }

    @GetMapping("/shop/product/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productService.getByName(name);
    }

    @GetMapping("/shop/products/{name}")
    public List<Product> getProductsByName(@PathVariable String name) {
        return productService.getByNameStartsWith(name);
    }

}
