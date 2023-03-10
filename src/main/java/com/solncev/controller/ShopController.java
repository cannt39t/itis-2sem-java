package com.solncev.controller;


import com.solncev.dto.ClientResponseDto;
import com.solncev.dto.CreateClientRequestDto;
import com.solncev.model.Category;
import com.solncev.model.Client;
import com.solncev.model.Order;
import com.solncev.model.Product;
import com.solncev.service.impl.CategoryServiceImpl;
import com.solncev.service.impl.ClientServiceImpl;
import com.solncev.service.impl.OrderServiceImpl;
import com.solncev.service.impl.ProductServiceImpl;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class ShopController {

    private final ClientServiceImpl clientService;
    private final CategoryServiceImpl categoryService;
    private final ProductServiceImpl productService;
    private final OrderServiceImpl orderService;

    @GetMapping("/shop")
    public String home() {
        return "shop";
    }

    @PostMapping("/client/create")
    public String createClient(@ModelAttribute CreateClientRequestDto client) {
        clientService.create(client);
        return "sign_up_success";
    }

    @ResponseBody
    @GetMapping("/client/{id}")
    public Client getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    @DeleteMapping("/client/{id}")
    public void deleteUser(@PathVariable Integer id) {
        clientService.deleteByIdNotNull(id);
    }

    @ResponseBody
    @GetMapping("/shop/category/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        return categoryService.getByIdNotNull(id);
    }

    @ResponseBody
    @GetMapping("/shop/category")
    public Category getCategoryByProduct(@Valid @RequestBody Product product) {
        return categoryService.findByProductsContains(product);
    }

    @ResponseBody
    @GetMapping("/shop/orders/{user_id}")
    public List<Order> getOrdersOfUserWithId(@PathVariable Integer user_id) {
        return orderService.getAllByClientId(user_id);
    }

    @ResponseBody
    @GetMapping("/shop/order/{user_id}")
    public Order getOrderById(@PathVariable Integer user_id) {
        return orderService.getOrderById(user_id);
    }

    @ResponseBody
    @GetMapping("/shop/product/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productService.getByName(name);
    }

    @ResponseBody
    @GetMapping("/shop/products/{name}")
    public List<Product> getProductsByName(@PathVariable String name) {
        return productService.getByNameStartsWith(name);
    }

}
