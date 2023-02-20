package com.nguyen.goldr_3.repository;

import com.nguyen.goldr_3.model.Category;
import com.nguyen.goldr_3.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

/*
testing custom query findByUserId in CategoryRepo
 */

@ExtendWith(MockitoExtension.class)
public class CategoryRepoTest {

    @Mock
    private CategoryRepo categoryRepo;

    @Test
    public void testFindByUserId() {
//        given
        User testUser = new User();
        testUser.setId(8);
        testUser.setEmail("test@test.com");

        Category testCategory = new Category();
        testCategory.setName("NFTs");
        testCategory.setUser(testUser);

        List<Category> expectedCategories = new ArrayList<>();
        expectedCategories.add(testCategory);

        when(categoryRepo.findByUserId(testUser.getId())).thenReturn(expectedCategories);

        // when
        List<Category> actualCategories = categoryRepo.findByUserId(testUser.getId());

        // then
        assertThat(actualCategories).isNotNull();
        assertThat(actualCategories.size()).isEqualTo(expectedCategories.size());
        assertThat(actualCategories).containsAll(expectedCategories);

    }

}
