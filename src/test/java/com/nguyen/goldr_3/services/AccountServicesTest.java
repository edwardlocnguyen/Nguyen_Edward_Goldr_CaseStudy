package com.nguyen.goldr_3.services;


/*
    testing method getAccountsByUserId in AccountServices
 */

import com.nguyen.goldr_3.model.Account;
import com.nguyen.goldr_3.model.Category;
import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.repository.AccountRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/*
    testing method getAccountsByUserId in AccountServices
 */

public class AccountServicesTest {

    private AccountServices accountServices;

    @Mock
    private AccountRepo accountRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        accountServices = new AccountServices();
        accountServices.setAccountRepo(accountRepo);
    }

    @Test
    public void testGetAccountsByUserId() {

//        given
        User testUser = new User();
        testUser.setId(8);

        Account testAccount = new Account();
        testAccount.setName("Checking_Account");
        testAccount.setUser(testUser);

        List<Account> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(testAccount);

        when(accountRepo.findByUserId(testUser.getId())).thenReturn(expectedAccounts);

//        when
        List<Account> actualAccounts = accountServices.getAccountsByUserId(testUser.getId());

//        then
        assertEquals(expectedAccounts, actualAccounts);

    }

}
