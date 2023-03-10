package com.nguyen.goldr_3.controller;

import com.nguyen.goldr_3.model.Entry;
import com.nguyen.goldr_3.repository.AccountRepo;
import com.nguyen.goldr_3.services.EntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public String createEntry(@PathVariable("userId") Integer userId, @ModelAttribute("entry") Entry entry) {
        Integer entry_account_id = entry.getAccount().getId();
        entryServices.addEntry(userId, entry);
        return "redirect:/users/" + userId + "/accounts-amounts";
    }

    @GetMapping
    public String userEntry(@PathVariable("userId") Integer userId, Model model) {

//        get user's entries
        List<Entry> userEntries = entryServices.getEntriesByUserId(userId);
//        reverse order of list
        List<Entry> reversedEntries = new ArrayList<>(userEntries);
        Collections.reverse(reversedEntries);

        model.addAttribute("reversedEntries", reversedEntries);

        return "entry";

    }

    @DeleteMapping("/{entryId}")
    public String deleteEntry(@PathVariable("userId") Integer userId, @PathVariable("entryId") Integer entryId) {
        entryServices.deleteEntry(entryId);
        return "redirect:/users/" + userId + "/entries";
    }

    @GetMapping("/api-page")
    public String apiPage() {
        return "api";
    }

}
