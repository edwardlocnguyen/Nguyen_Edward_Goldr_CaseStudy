package com.nguyen.goldr_3.controller;

import com.nguyen.goldr_3.model.Category;
import com.nguyen.goldr_3.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
    category controller includes methods to route user through category-related webpages
 */

@Controller
@RequestMapping("/users/{userId}/categories")
public class CategoryController {

    @Autowired
    private CategoryServices categoryServices;

    @PostMapping
    public String createCategory(@RequestParam("userId") Integer userId, @ModelAttribute("category") Category category) {
        categoryServices.addCategory(userId, category);
        return "redirect:/users/" + userId + "/categories-amounts";
    }

    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@RequestParam("userId") Integer userId, @PathVariable("categoryId") Integer categoryId) {
        categoryServices.deleteCategory(categoryId);
        return "redirect:/users/" + userId + "/categories-amounts";
    }

}
