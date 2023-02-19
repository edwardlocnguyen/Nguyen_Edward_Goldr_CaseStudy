package com.nguyen.goldr_3.services;

import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepo userRepo;

    public Optional<User> getUserById(Integer userId) {

        try {
            return userRepo.findById(userId);

        } catch (Exception e) {
            System.err.println("Error occurred while getting user by ID: " + e.getMessage());
            return Optional.empty();
        }
    }

//    need to implement
    public void addUser(User user) {
        userRepo.save(user);
    }

    public void updateUser(Integer id, User user) {
        Optional<User> userData = userRepo.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();

            if (!user.getEmail().isEmpty()) {
                _user.setEmail(user.getEmail());
            }
            if (!user.getPassword().isEmpty()) {
                _user.setPassword(user.getPassword());
            }
            if (!user.getFirstName().isEmpty()) {
                _user.setFirstName(user.getFirstName());
            }
            if (!user.getLastName().isEmpty()) {
                _user.setLastName(user.getLastName());
            }
            if (!user.getOccupation().isEmpty()) {
                _user.setOccupation(user.getOccupation());
            }
            if (user.getDob() != null) {
                _user.setDob(user.getDob());
            }
            if (user.getCreditScore() != null) {
                _user.setCreditScore(user.getCreditScore());
            }

            userRepo.save(_user);
        }
    }

//    need to implement
    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }

}
