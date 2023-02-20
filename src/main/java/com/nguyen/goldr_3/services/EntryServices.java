package com.nguyen.goldr_3.services;

import com.nguyen.goldr_3.model.Account;
import com.nguyen.goldr_3.model.Entry;
import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.repository.AccountRepo;
import com.nguyen.goldr_3.repository.EntryRepo;
import com.nguyen.goldr_3.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
    entry services include methods to perform CRUD operations on entries
 */

@Service
public class EntryServices {

    @Autowired
    private EntryRepo entryRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AccountRepo accountRepo;

    public List<Entry> getEntriesByUserId(Integer userId) {
        return entryRepo.findByUserId(userId);
    }

    public void addEntry(Integer userId, Entry entry) {
        Optional<User> user = userRepo.findById(userId);

        if (user.isPresent()) {
            try {
                Optional<Account> account = accountRepo.findById(entry.getAccount().getId());
                if (account.isPresent()) {
                    entry.setUser(user.get());
                    entry.setAccount(account.get());
                    entryRepo.save(entry);
                }

            } catch (NullPointerException e) {
                System.out.println("Account is null");
            }
        }
    }

    public void updateEntry(Integer entryId, Entry entry) {
        Optional<Entry> entryData = entryRepo.findById(entryId);

        if (entryData.isPresent()) {
            Entry _entry = entryData.get();

            if (entry.getAmount() != 0.0) {
                _entry.setAmount(entry.getAmount());
            }
            if (entry.getDate() != null) {
                _entry.setDate(entry.getDate());
            }
            if (entry.getAccount() != null) {
                _entry.setAccount(entry.getAccount());
            }

            entryRepo.save(_entry);
        }
    }

    public void deleteEntry(Integer entryId) {
        entryRepo.deleteById(entryId);
    }

}
