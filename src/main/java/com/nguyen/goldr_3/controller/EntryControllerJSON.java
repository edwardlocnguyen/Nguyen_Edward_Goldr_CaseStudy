package com.nguyen.goldr_3.controller;

import com.nguyen.goldr_3.model.Entry;
import com.nguyen.goldr_3.services.EntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping
public class EntryControllerJSON {

    @Autowired
    private EntryServices entryServices;

    // fxn used in getAllEntries method
    public List<Map<String, Object>> convertEntriesToMap(List<Entry> allEntries) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (Entry entry : allEntries) {
            Map<String, Object> entryMap = new LinkedHashMap<>();
            entryMap.put("entryId", entry.getId());
            entryMap.put("entryAmount", entry.getAmount());
            entryMap.put("entryDate", entry.getDate());
            entryMap.put("entry_AccountCategoryId", entry.getAccount().getCategory().getId());
            entryMap.put("entry_AccountCategoryName", entry.getAccount().getCategory().getName());
            entryMap.put("entry_UserCreditScore", entry.getUser().getCreditScore());
            entryMap.put("entry_UserDob", entry.getUser().getDob());

            result.add(entryMap);
        }

        return result;
    }


    //    used for API page
    @GetMapping("entries/api-data")
    public List<Map<String, Object>> getAllEntries() {
        List<Entry> allEntries = entryServices.getAllEntries();
        return convertEntriesToMap(allEntries);
    }

    @GetMapping("users/{userId}/entries/api-data")
    public List<Entry> getEntriesByUserId(@PathVariable("userId") Integer userId) {
        return entryServices.getEntriesByUserId(userId);
    }

}
