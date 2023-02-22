package com.nguyen.goldr_3.services;

import com.nguyen.goldr_3.model.Category;
import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.repository.CategoryRepo;
import com.nguyen.goldr_3.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
    category services include methods to perform CRUD operations on categories
 */

@Service
public class CategoryServices {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;

    public List<Category> getCategoriesByUserId(Integer userId) {
        return categoryRepo.findByUserId(userId);
    }

    public void addCategory(Integer userId, Category category) {
        Optional<User> user = userRepo.findById(userId);

        if (user.isPresent()) {
            category.setUser(user.get());
            categoryRepo.save(category);
        }
    }

    public void updateCategory(Integer categoryId, Category category) {
        Optional<Category> categoryData = categoryRepo.findById(categoryId);

        if (categoryData.isPresent()) {
            Category _category = categoryData.get();

            if (!category.getName().isEmpty()) {
                _category.setName(category.getName());
            }

            categoryRepo.save(_category);
        }
    }

    public void deleteCategory(Integer categoryId) {
        categoryRepo.deleteById(categoryId);
    }

//    for CategoryServicesTest
    public void setCategoryRepo(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

}
