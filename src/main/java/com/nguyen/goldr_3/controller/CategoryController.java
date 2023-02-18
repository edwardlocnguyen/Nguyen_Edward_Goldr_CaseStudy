package com.nguyen.goldr_3.controller;

import com.nguyen.goldr_3.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users/{userId}/categories")
public class CategoryController {

    @Autowired
    private CategoryServices categoryServices;

    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@RequestParam("userId") Integer userId, @PathVariable("categoryId") Integer categoryId) {
        categoryServices.deleteCategory(categoryId);
        return "redirect:/users/" + userId + "/categories-amounts";
    }

}
