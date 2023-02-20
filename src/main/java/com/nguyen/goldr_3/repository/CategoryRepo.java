package com.nguyen.goldr_3.repository;

import com.nguyen.goldr_3.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    List<Category> findByUserId(Integer userId);

}
