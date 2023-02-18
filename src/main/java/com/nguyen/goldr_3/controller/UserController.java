package com.nguyen.goldr_3.controller;

import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Controller
@RequestMapping("/users/{userId}")
public class UserController {

    @Autowired
    private UserServices userServices;

//    fxn to get the user's age
    public int calculateUserAge(User _user) {
        LocalDate dob = _user.getDob();
        int age = (int) ChronoUnit.YEARS.between(dob, LocalDate.now());
        return age;
    }

    @GetMapping("/profile")
    public String profile(@PathVariable("userId") Integer userId, Model model) {

//        get user data
        Optional<User> user = userServices.getUserById(userId);
        User _user = user.get();
        int userAge = calculateUserAge(_user);

        model.addAttribute("user", _user);
        model.addAttribute("userId", userId.toString());
        model.addAttribute("userAge", userAge);

        return "profile";

    }

}
