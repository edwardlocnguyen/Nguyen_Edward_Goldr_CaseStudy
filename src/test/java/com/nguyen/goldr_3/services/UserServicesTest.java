package com.nguyen.goldr_3.services;

import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/*
testing method getUserById in UserServices
 */

@ExtendWith(MockitoExtension.class)
public class UserServicesTest {

    @Mock
    private UserRepo userRepo;

    @Test
    public void testGetUserById() {
//        given
        User expectedUser = new User();
        expectedUser.setId(8);

        when(userRepo.findById(expectedUser.getId())).thenReturn(Optional.of(expectedUser));

        UserServices userServices = new UserServices();
        userServices.setUserRepo(userRepo);

//        when
        Optional<User> actualUser = userServices.getUserById(expectedUser.getId());

//        then
        assertEquals(expectedUser.getId(), actualUser.get().getId());

    }

}
