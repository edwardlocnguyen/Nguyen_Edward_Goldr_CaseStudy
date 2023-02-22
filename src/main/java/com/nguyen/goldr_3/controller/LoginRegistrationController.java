package com.nguyen.goldr_3.controller;

import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.repository.UserRepo;
import com.nguyen.goldr_3.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
    * LoginRegistration controller includes methods to route user through login and registration webpages
    * the login and register routes invoke methods in the UserServices that use bcrypt for security
 */

@Controller
public class LoginRegistrationController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute("user") User user) {
        userServices.addUser(user);

        int userId = user.getId();
        return "redirect:/users/" + userId + "/home";
    }

    @GetMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        User user = userServices.login(email, password);
        if (user != null) {
            int userId = user.getId();
            return "redirect:/users/" + userId + "/home";
        } else {
            model.addAttribute("loginError", "Invalid email or password");
            return "index";
        }
    }

    @GetMapping("/signout")
    public String signout() {
        return "index";
    }

}
