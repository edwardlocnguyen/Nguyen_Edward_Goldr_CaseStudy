package com.nguyen.goldr_3.controller;

import com.nguyen.goldr_3.model.Account;
import com.nguyen.goldr_3.model.Category;
import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.services.AccountServices;
import com.nguyen.goldr_3.services.CategoryServices;
import com.nguyen.goldr_3.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users/{userId}")
public class UserController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private CategoryServices categoryServices;
    @Autowired
    private AccountServices accountServices;

//    fxn to get the user's age
    public int calculateUserAge(User _user) {
        LocalDate dob = _user.getDob();
        int age = (int) ChronoUnit.YEARS.between(dob, LocalDate.now());
        return age;
    }

//    goes to user profile page
    @GetMapping("/profile")
    public String userProfile(@PathVariable("userId") Integer userId, Model model) {

//        get user data
        Optional<User> user = userServices.getUserById(userId);
        User _user = user.get();
        int userAge = calculateUserAge(_user);

        model.addAttribute("user", _user);
        model.addAttribute("userAge", userAge);

        return "profile";

    }

//    used in user profile page
    @PutMapping("/update")
    public String updateUser(@PathVariable("userId") Integer userId, @ModelAttribute User user) {
        userServices.updateUser(userId, user);
        return "redirect:/users/" + userId + "/profile";
    }

//    goes to user accounts page
    @GetMapping("/accounts-amounts")
    public String userAccount(@PathVariable("userId") Integer userId, Model model) {

//        get the user's accounts for the delete buttons
        List<Account> userAccounts = accountServices.getAccountsByUserId(userId);

        model.addAttribute("account", new Account());
        model.addAttribute("userAccounts", userAccounts);

        return "account";
    }


//    goes to user categories page
    @GetMapping("/categories-amounts")
    public String userCategory(@PathVariable("userId") Integer userId, Model model) {

//        get the user's categories for the delete buttons
        List<Category> userCategories = categoryServices.getCategoriesByUserId(userId);

        model.addAttribute("category", new Category());
        model.addAttribute("userCategories", userCategories);

        return "category";
    }

}
