package com.solncev.controller;

import com.solncev.dto.CreateUserRequestDto;
import com.solncev.dto.UserResponseDto;
import com.solncev.model.Client;
import com.solncev.security.CustomClientDetails;
import com.solncev.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseBody
    @GetMapping(value = {"/users/{id}", "users"})
    public List<UserResponseDto> user(@PathVariable(required = false) Optional<Integer> id) {
        if (id.isPresent()) {
            return List.of(userService.findById(id.get()).get());
        } else {
            return userService.findAll();
        }
    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello(@RequestParam Optional<String> name) {
        return String.format("Hello, %s!", name.orElse("Ivan"));
    }

    @PostMapping("/user")
    public String createUser(@ModelAttribute CreateUserRequestDto user) {
        userService.create(user);
        return "sign_up_success";
    }
}
