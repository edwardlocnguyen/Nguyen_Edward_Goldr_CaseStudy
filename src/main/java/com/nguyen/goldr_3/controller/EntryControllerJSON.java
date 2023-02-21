package com.nguyen.goldr_3.controller;

import com.nguyen.goldr_3.model.Entry;
import com.nguyen.goldr_3.services.EntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class EntryControllerJSON {

    @Autowired
    private EntryServices entryServices;

    //    used for API page
    @GetMapping("entries/api-data")
    public List<Entry> getAllEntries() {
        return entryServices.getAllEntries();
    }

    @GetMapping("users/{userId}/entries/api-data")
    public List<Entry> getEntriesByUserId(@PathVariable("userId") Integer userId) {
        return entryServices.getEntriesByUserId(userId);
    }

}
