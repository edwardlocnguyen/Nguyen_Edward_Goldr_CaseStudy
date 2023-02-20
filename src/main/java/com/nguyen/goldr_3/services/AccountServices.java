package com.nguyen.goldr_3.services;

import com.nguyen.goldr_3.model.Account;
import com.nguyen.goldr_3.model.Category;
import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.repository.AccountRepo;
import com.nguyen.goldr_3.repository.CategoryRepo;
import com.nguyen.goldr_3.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
    account services include methods to perform CRUD operations on accounts
 */

@Service
public class AccountServices {

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    public List<Account> getAccountsByUserId(Integer userId) {
        return accountRepo.findByUserId(userId);
    }

    public void addAccount(Integer userId, Account account) {
        Optional<User> user = userRepo.findById(userId);

        if (user.isPresent()) {
            try {
                Optional<Category> category = categoryRepo.findById(account.getCategory().getId());
                if (category.isPresent()) {
                    account.setUser(user.get());
                    account.setCategory(category.get());
                    accountRepo.save(account);
                }
            } catch (NullPointerException e) {
                System.out.println("Category is null");
            }
        }
    }

    public void updateAccount(Integer accountId, Account account) {
        Optional<Account> accountData = accountRepo.findById(accountId);

        if (accountData.isPresent()) {
            Account _account = accountData.get();

            if (!account.getName().isEmpty()) {
                _account.setName(account.getName());
            }

            accountRepo.save(_account);
        }
    }

    public void deleteAccount(Integer AccountId) {
        accountRepo.deleteById(AccountId);
    }

    //    for AccountServicesTest
    public void setAccountRepo(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }
}
