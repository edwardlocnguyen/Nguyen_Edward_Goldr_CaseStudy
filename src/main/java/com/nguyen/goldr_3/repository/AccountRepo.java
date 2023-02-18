package com.nguyen.goldr_3.repository;

import com.nguyen.goldr_3.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

    List<Account> findByUserId(Integer userId);
}
