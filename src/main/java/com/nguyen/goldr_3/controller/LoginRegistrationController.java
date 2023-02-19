package com.nguyen.goldr_3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginRegistrationController {

    @GetMapping("/register")
    public String register(Model model) {


        return "register";
    }

}
