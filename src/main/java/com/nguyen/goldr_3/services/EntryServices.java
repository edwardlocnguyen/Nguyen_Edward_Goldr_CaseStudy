package com.nguyen.goldr_3.services;

import com.nguyen.goldr_3.model.Account;
import com.nguyen.goldr_3.model.Entry;
import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.repository.AccountRepo;
import com.nguyen.goldr_3.repository.EntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntryServices {

    @Autowired
    private EntryRepo entryRepo;
    @Autowired
    private AccountRepo accountRepo;

    public List<Entry> getEntriesByAccountId(Integer accountId) {
        return entryRepo.findByAccountId(accountId);
    }

    public void addEntry(Integer accountId, Entry entry) {
        Optional<Account> account = accountRepo.findById(accountId);

        if (account.isPresent()) {
            entry.setAccount(account.get());
            entryRepo.save(entry);
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
