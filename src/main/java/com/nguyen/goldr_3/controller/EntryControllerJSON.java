//package com.nguyen.goldr_3.controller;
//
//import com.nguyen.goldr_3.model.Entry;
//import com.nguyen.goldr_3.services.EntryServices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("users/{userId}/entries/api")
//public class EntryControllerJSON {
//
//    @Autowired
//    private EntryServices entryServices;
//
//    @GetMapping
//    public List<Entry> getEntriesByUserId(@PathVariable("userId") Integer userId) {
//        return entryServices.getEntriesByUserId(userId);
//    }
//
//}
