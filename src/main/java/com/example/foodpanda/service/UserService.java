package com.example.foodpanda.service;

import com.example.foodpanda.entity.User;
import com.example.foodpanda.logger.MyLogger;
import com.example.foodpanda.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class UserService
 * Handles all the operations related to the user
 */
@Service
public class UserService{
    private static final Logger LOGGER = MyLogger.getInstance();
    private final UserRepository userRepository;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository the user repository
     */
    @Autowired
    public UserService(UserRepository userRepository){this.userRepository = userRepository;}

    /**
     * Find all users
     *
     * @return the list of users
     */
    public List<User> findAll(){
       LOGGER.info("Finding all users");
       return userRepository.findAll();
    }

    /**
     * Save the user int the database
     *
     * @param user the user we want to save
     */
    public void save(User user){
        LOGGER.info("Saving user");
        userRepository.save(user);
    }


    /**
     * Find user by username
     *
     * @param username the username
     * @return the user found
     */
    public User findByUsername(String username){
        LOGGER.info("Finding user with username {}", username);
        return userRepository.findByUsername(username);
    }

}
