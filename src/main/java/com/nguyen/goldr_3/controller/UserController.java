package com.nguyen.goldr_3.controller;

import com.nguyen.goldr_3.model.Account;
import com.nguyen.goldr_3.model.Category;
import com.nguyen.goldr_3.model.Entry;
import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.services.AccountServices;
import com.nguyen.goldr_3.services.CategoryServices;
import com.nguyen.goldr_3.services.EntryServices;
import com.nguyen.goldr_3.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
@RequestMapping("/users/{userId}")
public class UserController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private CategoryServices categoryServices;
    @Autowired
    private AccountServices accountServices;
    @Autowired
    private EntryServices entryServices;

    //    fxn to get the user's age
    public int calculateUserAge(User _user) {
        LocalDate dob = _user.getDob();
        int age = (int) ChronoUnit.YEARS.between(dob, LocalDate.now());
        return age;
    }

    //    fxn to get user's latest entries per account
    public List<Map<String, Object>> getLatestEntriesPerAccount(List<Entry> userLatestEntriesPerAccount) {
        // create a map to store the latest entry for each account
        Map<Integer, Entry> latestEntries = new HashMap<>();

        // loop through each entry in the user's list of entries
        for (Entry entry : userLatestEntriesPerAccount) {
            // get the account ID for the current entry
            int accountId = entry.getAccount().getId();

            // check if there is already an entry for this account
            Entry latestEntry = latestEntries.getOrDefault(accountId, null);

            // if there is no existing entry or the current entry is more recent, update the latest entry for this account
            if (latestEntry == null || entry.getDate().isAfter(latestEntry.getDate())) {
                latestEntries.put(accountId, entry);
            }
        }

        // create a list to store the result
        List<Map<String, Object>> result = new ArrayList<>();

        // loop through the latest entry for each account and create a map with the desired keys/values
        for (Entry entry : latestEntries.values()) {
            Map<String, Object> entryMap = new HashMap<>();
            entryMap.put("entryId", entry.getId());
            entryMap.put("entryAmount", String.format("%.2f", entry.getAmount()));
            entryMap.put("entryDate", entry.getDate().toString());
            entryMap.put("entryAccountId", entry.getAccount().getId());
            entryMap.put("entryAccountName", entry.getAccount().getName());
            entryMap.put("entryAccountCategoryId", entry.getAccount().getCategory().getId());
            entryMap.put("entryAccountCategoryName", entry.getAccount().getCategory().getName());
            result.add(entryMap);
        }

        return result;
    }

    //    fxn to get the total amount from the latest entries
    public double getTotalAmount(List<Map<String, Object>> latestEntriesPerAccount) {
        double totalAmount = 0.0;

        for (Map<String, Object> entry : latestEntriesPerAccount) {
            double amount = Double.parseDouble((String) entry.get("entryAmount"));
            totalAmount += amount;
        }

        return totalAmount;
    }

    //    fxn to get category amounts from latest entries
    public List<Map<String, Object>> getCategoryAmountList(List<Map<String, Object>> latestEntriesPerAccount) {
        // map to store the category ID as key and category details (ID, name and amount) as value
        Map<Integer, Map<String, Object>> categoryMap = new HashMap<>();

        for (Map<String, Object> entry : latestEntriesPerAccount) {
            // get the category ID from the latest entry
            int categoryId = (int) entry.get("entryAccountCategoryId");

            // get the category map from the categoryMap map, or create a new empty map
            Map<String, Object> category = categoryMap.getOrDefault(categoryId, new HashMap<>());

            // update the category details with the new entry amount and account name
            category.put("categoryId", categoryId);
            category.put("categoryName", entry.get("entryAccountCategoryName"));
            category.put("categoryAmount", (double) category.getOrDefault("categoryAmount", 0.0) + Double.parseDouble((String) entry.get("entryAmount")));

            // put the updated category map back to the categoryMap map
            categoryMap.put(categoryId, category);
        }

        // create a list to store the result
        List<Map<String, Object>> result = new ArrayList<>();

        // loop through each category map in categoryMap map
        for (Map<String, Object> category : categoryMap.values()) {
            // add the category map to the result list
            result.add(category);
        }

        // return the result list
        return result;
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

//    goes to user home page
    @GetMapping("/home")
    public String userHome(@PathVariable("userId") Integer userId, Model model) {

//        get user data
        Optional<User> user = userServices.getUserById(userId);
        User _user = user.get();
        int userAge = calculateUserAge(_user);

//        get user's entries
        List<Entry> userEntries = entryServices.getEntriesByUserId(userId);
//        get user's latest entries per account for the account card
        List<Map<String, Object>> latestEntriesPerAccount = getLatestEntriesPerAccount(userEntries);
//        get user's latest entries per account total amount for the account card
        double latestEntriesPerAccountTotalAmount = getTotalAmount(latestEntriesPerAccount);
        String formattedTotalAmount = String.format("%.2f", latestEntriesPerAccountTotalAmount);

//        get user's category amounts for the table
        List<Map<String, Object>> categoryAmountList = getCategoryAmountList(latestEntriesPerAccount);
//        loop through the categoryAmountList to add the categoryAllocation key
        for (Map<String, Object> categoryAmount : categoryAmountList) {
            double categoryAmountValue = (double) categoryAmount.get("categoryAmount");
            double categoryAllocation = (categoryAmountValue / latestEntriesPerAccountTotalAmount) * 100.0;
            String formattedCategoryAmount = String.format("%.2f", categoryAmountValue);
            categoryAmount.put("categoryAmount", formattedCategoryAmount);
            categoryAmount.put("categoryAllocation", String.format("%.2f", categoryAllocation) + "%");
        }

        model.addAttribute("user", _user);
        model.addAttribute("userAge", userAge);
        model.addAttribute("latestEntriesPerAccount", latestEntriesPerAccount);
        model.addAttribute("formattedTotalAmount", formattedTotalAmount);
        model.addAttribute("categoryAmountList", categoryAmountList);

        return "home";
    }

//    goes to user accounts page
    @GetMapping("/accounts-amounts")
    public String userAccount(@PathVariable("userId") Integer userId, Model model) {

//        get user's accounts for the delete buttons and create entry form
        List<Account> userAccounts = accountServices.getAccountsByUserId(userId);
//        get user's categories for the create entry form
        List<Category> userCategories = categoryServices.getCategoriesByUserId(userId);

//        get user's entries
        List<Entry> userEntries = entryServices.getEntriesByUserId(userId);
//        get user's latest entries per account for the table
        List<Map<String, Object>> latestEntriesPerAccount = getLatestEntriesPerAccount(userEntries);
//        get user's latest entries per account total amount for the table
        double latestEntriesPerAccountTotalAmount = getTotalAmount(latestEntriesPerAccount);
        String formattedTotalAmount = String.format("%.2f", latestEntriesPerAccountTotalAmount);

        model.addAttribute("account", new Account());
        model.addAttribute("entry", new Entry());
        model.addAttribute("userAccounts", userAccounts);
        model.addAttribute("userCategories", userCategories);
        model.addAttribute("latestEntriesPerAccount", latestEntriesPerAccount);
        model.addAttribute("formattedTotalAmount", formattedTotalAmount);

        return "account";
    }

    //    goes to user categories page
    @GetMapping("/categories-amounts")
    public String userCategory(@PathVariable("userId") Integer userId, Model model) {

//        get the user's categories for the delete buttons
        List<Category> userCategories = categoryServices.getCategoriesByUserId(userId);

//        get user's entries
        List<Entry> userEntries = entryServices.getEntriesByUserId(userId);
//        get user's latest entries per account
        List<Map<String, Object>> latestEntriesPerAccount = getLatestEntriesPerAccount(userEntries);
//        get user's latest entries per account total amount for the table
        double latestEntriesPerAccountTotalAmount = getTotalAmount(latestEntriesPerAccount);
        String formattedTotalAmount = String.format("%.2f", latestEntriesPerAccountTotalAmount);

//        get user's category amounts for the table
        List<Map<String, Object>> categoryAmountList = getCategoryAmountList(latestEntriesPerAccount);
//        loop through the categoryAmountList to add the categoryAllocation key
        for (Map<String, Object> categoryAmount : categoryAmountList) {
            double categoryAmountValue = (double) categoryAmount.get("categoryAmount");
            double categoryAllocation = (categoryAmountValue / latestEntriesPerAccountTotalAmount) * 100.0;
            String formattedCategoryAmount = String.format("%.2f", categoryAmountValue);
            categoryAmount.put("categoryAmount", formattedCategoryAmount);
            categoryAmount.put("categoryAllocation", String.format("%.2f", categoryAllocation) + "%");
        }

        model.addAttribute("category", new Category());
        model.addAttribute("userCategories", userCategories);
        model.addAttribute("formattedTotalAmount", formattedTotalAmount);
        model.addAttribute("categoryAmountList", categoryAmountList);

        return "category";
    }

}
