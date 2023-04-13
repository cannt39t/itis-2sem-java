package com.solncev.controller;

import com.solncev.dto.CreateClientRequestDto;
import com.solncev.dto.CreateUserRequestDto;
import com.solncev.model.Client;
import com.solncev.security.CustomClientDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

//    @GetMapping("/sign_up")
//    public String signUp(Model model) {
//        model.addAttribute("user", new CreateUserRequestDto());
//        return "sign_up";
//    }

    @GetMapping("/sign_up")
    public String createClient(Model model) {
        model.addAttribute("client", new CreateClientRequestDto());
        return "create_client";
    }
}
