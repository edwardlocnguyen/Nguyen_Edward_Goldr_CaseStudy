package com.nguyen.goldr_3.controller;

import com.nguyen.goldr_3.model.Account;
import com.nguyen.goldr_3.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users/{userId}/accounts")
public class AccountController {

    @Autowired
    private AccountServices accountServices;

    @PostMapping
    public String createAccount(@RequestParam("userId") Integer userId, @ModelAttribute("account") Account account) {
        accountServices.addAccount(userId, account);
        return "redirect:/users/" + userId + "/accounts-amounts";
    }
}
