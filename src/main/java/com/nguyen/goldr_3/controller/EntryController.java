package com.nguyen.goldr_3.controller;

import com.nguyen.goldr_3.model.Account;
import com.nguyen.goldr_3.model.Entry;
import com.nguyen.goldr_3.repository.AccountRepo;
import com.nguyen.goldr_3.services.EntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
    entry controller includes methods to route user through entry-related webpages
 */

@Controller
@RequestMapping("users/{userId}/entries")
public class EntryController {

    @Autowired
    private EntryServices entryServices;
    @Autowired
    private AccountRepo accountRepo;

    //    request param gets value from URL
    @PostMapping
    public String createEntry(@RequestParam("userId") Integer userId, @ModelAttribute("entry") Entry entry) {
        Integer entry_account_id = entry.getAccount().getId();
        System.out.println("entry_account_id: " + entry_account_id);
        entryServices.addEntry(userId, entry);
        return "redirect:/users/" + userId + "/accounts-amounts";
    }

}
