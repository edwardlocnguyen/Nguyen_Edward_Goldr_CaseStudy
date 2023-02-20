package com.nguyen.goldr_3.repository;

import com.nguyen.goldr_3.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/*
testing custom query findByEmail in UserRepo
 */

@ExtendWith(MockitoExtension.class)
public class UserRepoTest {

    @Mock
    private UserRepo userRepo;

    @Test
    public void testFindByEmail() {
        // given
        String email = "test@test.com";
        User user = new User();
        user.setEmail(email);

        when(userRepo.findByEmail(email)).thenReturn(Optional.of(user));

        // when
        Optional<User> found = userRepo.findByEmail(email);

        // then
        assertEquals(found.get().getEmail(), email);
    }
}