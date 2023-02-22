package com.nguyen.goldr_3.services;

import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/*
    * testing method getUserById in UserServices
    * also a parameterized test testing different user IDs
    * testGetByUserId retrieves a user by their ID from the UserRepo, and verifies that the returned user has the expected ID
 */

//  initialize and inject mocks into class
@ExtendWith(MockitoExtension.class)
public class UserServicesTest {

//    mockito creates a mock UserRepo obj to simulate the DB
    @Mock
    private UserRepo userRepo;

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    public void testGetUserById(int userId) {
//        given
        User expectedUser = new User();
        expectedUser.setId(userId);

//        tell mockito to return the expectedUser when the findById method is invoked by the mock obj userRepo
        when(userRepo.findById(expectedUser.getId())).thenReturn(Optional.of(expectedUser));

        UserServices userServices = new UserServices();
        userServices.setUserRepo(userRepo);

//        when
        Optional<User> actualUser = userServices.getUserById(expectedUser.getId());

//        then
        assertEquals(expectedUser.getId(), actualUser.get().getId());

    }

}
