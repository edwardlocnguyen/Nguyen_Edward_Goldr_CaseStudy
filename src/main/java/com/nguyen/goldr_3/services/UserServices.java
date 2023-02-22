package com.nguyen.goldr_3.services;

import com.nguyen.goldr_3.model.Category;
import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

/*
    user services include methods to perform CRUD operations on users
 */

@Service
public class UserServices {

//    constant variable
    private static final Logger logger = LoggerFactory.getLogger(UserServices.class);

//    field injection to create a managed bean
    @Autowired
    private UserRepo userRepo;
    @Autowired
    CategoryServices categoryServices;

    public Optional<User> getUserById(Integer userId) {
        try {
            return userRepo.findById(userId);
        } catch (Exception e) {
            logger.error("Error occurred while getting user by ID: {}", e.getMessage());
            return Optional.empty();
        }
    }

    public void addUser(User user) {
//        using bcrypt to encode the new user's password
        int scramble = 8; // strength parameter - number of times to hash password
        BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder(scramble, new SecureRandom());
        String scrambled_pw = bcryptEncoder.encode(user.getPassword());
        user.setPassword(scrambled_pw);

        userRepo.save(user);

//        create default categories for new user
        Integer userId = user.getId();

        Category cash = new Category();
        cash.setName("Cash");
        Category creditCards = new Category();
        creditCards.setName("Credit Cards");
        Category loans = new Category();
        loans.setName("Loans");
        Category investments = new Category();
        investments.setName("Investments");
        Category property = new Category();
        property.setName("Property");

        categoryServices.addCategory(userId, cash);
        categoryServices.addCategory(userId, creditCards);
        categoryServices.addCategory(userId, loans);
        categoryServices.addCategory(userId, investments);
        categoryServices.addCategory(userId, property);

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

    //    to authenticate the login user
    public User login(String email, String password) {
        Optional<User> userData = userRepo.findByEmail(email);
        User user = null;

        try {
            if (userData.isPresent()) {
                User _user = userData.get();

                BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

//                bcrypt decoder
                if (bcryptEncoder.matches(password, _user.getPassword())) {
                    user = _user;
                }
            }
        } catch (Exception e) {
            logger.error("An error occurred while trying to log in: " + e.getMessage());
        }

        if (user == null) {
            logger.info("User not found for email: " + email);
        }

        return user;
    }

    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }

//    for UserServicesTest
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

}
