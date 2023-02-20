package com.nguyen.goldr_3.repository;

import com.nguyen.goldr_3.model.Account;
import com.nguyen.goldr_3.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/*
    testing custom query findByUserId in AccountRepo
 */

@ExtendWith(MockitoExtension.class)
public class AccountRepoTest {

    @Mock
    private AccountRepo accountRepo;

    @Test
    public void testFindByUserId() {
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
        List<Account> actualAccounts = accountRepo.findByUserId(testUser.getId());

//        then
        assertThat(actualAccounts).isNotNull();
        assertThat(actualAccounts.size()).isEqualTo(expectedAccounts.size());
        assertThat(actualAccounts).containsAll(expectedAccounts);

    }

}
