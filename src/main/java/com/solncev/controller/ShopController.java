package com.solncev.controller;


import com.solncev.dto.ClientResponseDto;
import com.solncev.dto.CreateClientRequestDto;
import com.solncev.model.Category;
import com.solncev.model.Client;
import com.solncev.model.Order;
import com.solncev.model.Product;
import com.solncev.security.CustomClientDetails;
import com.solncev.service.impl.CategoryServiceImpl;
import com.solncev.service.impl.ClientServiceImpl;
import com.solncev.service.impl.OrderServiceImpl;
import com.solncev.service.impl.ProductServiceImpl;
import lombok.AllArgsConstructor;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public String createClient(@ModelAttribute CreateClientRequestDto client, HttpServletRequest request) {
        String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
        clientService.create(client, url);
        return "sign_up_success";
    }

//    @PostMapping("/user")
//    public String createUser(@ModelAttribute CreateUserRequestDto user, HttpServletRequest request) {
//        String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
//        userService.create(user, url);
//        return "sign_up_success";
//    }

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

    @GetMapping("/orders")
    public String getOrdersOfUserWithId(@AuthenticationPrincipal CustomClientDetails customClientDetails, Model model) {
        String name = customClientDetails.getUsername();
        Client client = clientService.getClientByName(name);
        List<Order> orders = orderService.getAllByClientId(client.getId());
        System.out.println(orders);
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/profile")
    public String showProfilePage(@AuthenticationPrincipal CustomClientDetails customClientDetails, Model model) {
        String name = customClientDetails.getUsername();
        Client client = clientService.getClientByName(name);
        ClientResponseDto clientResponse = ClientResponseDto.fromEntity(client);
        model.addAttribute("client", clientResponse);
        return "personal_cabinet";
    }

    @GetMapping("/verification")
    public String verifaction(@Param("code") String code) {
        System.out.println("Verify");
        if (clientService.verify(code)) {
            return "verification_success";
        } else {
            return "verification_failed";
        }
    }

}
