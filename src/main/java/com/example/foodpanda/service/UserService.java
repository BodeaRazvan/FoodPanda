package com.example.foodpanda.service;

import com.example.foodpanda.entity.User;
import com.example.foodpanda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){this.userRepository = userRepository;}

    public List<User> findAll(){
       return userRepository.findAll();
    }

    public void save(User user){
        userRepository.save(user);
    }


    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
