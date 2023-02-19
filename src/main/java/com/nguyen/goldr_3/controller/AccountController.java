package com.nguyen.goldr_3.controller;

import com.nguyen.goldr_3.model.Account;
import com.nguyen.goldr_3.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{accountId}")
    public String deleteAccount(@RequestParam("userId") Integer userId, @PathVariable("accountId") Integer accountId) {
        accountServices.deleteAccount(accountId);
        return "redirect:/users/" + userId + "/accounts-amounts";
    }

}