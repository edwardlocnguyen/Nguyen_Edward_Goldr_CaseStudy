package com.nguyen.goldr_3.services;

import com.nguyen.goldr_3.model.Category;
import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.repository.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/*
    testing method getCategoriesByUserId in CategoryServices
 */

public class CategoryServicesTest {

    private CategoryServices categoryServices;

    @Mock
    private CategoryRepo categoryRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        categoryServices = new CategoryServices();
        categoryServices.setCategoryRepo(categoryRepo);
    }

    @Test
    public void testGetCategoriesByUserId() {
//        given
        User testUser = new User();
        testUser.setId(8);

        Category testCategory = new Category();
        testCategory.setName("NFTs");
        testCategory.setUser(testUser);

        List<Category> expectedCategories = new ArrayList<>();
        expectedCategories.add(testCategory);

        when(categoryRepo.findByUserId(testUser.getId())).thenReturn(expectedCategories);

//        when
        List<Category> actualCategories = categoryServices.getCategoriesByUserId(testUser.getId());

//        then
        assertEquals(expectedCategories, actualCategories);

    }

}
