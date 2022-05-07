package com.example.foodpanda.controller;

import com.example.foodpanda.dto.RegisterDTO;
import com.example.foodpanda.dto.UserMapper;
import com.example.foodpanda.entity.User;
import com.example.foodpanda.logger.MyLogger;
import com.example.foodpanda.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class RegisterController {

    private static final Logger LOGGER = MyLogger.getInstance();
    @Autowired
    private final UserService userService;
    private final UserMapper userMapper = UserMapper.getInstance();

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value ="/register",consumes = "application/json")
    public ResponseEntity<User> registerUser(@RequestBody RegisterDTO registerDTO){
        LOGGER.info("Request for registering user");
        if(registerDTO.getAddress().equals("")||registerDTO.getEmail().equals("")||registerDTO.getPassword1().equals("")||registerDTO.getPassword2().equals("")||registerDTO.getUsername().equals("")){
            LOGGER.info("Empty fields");
            return ResponseEntity.badRequest().body(null);
        }
        if(!registerDTO.getPassword1().equals(registerDTO.getPassword2())){
            LOGGER.info("Passwords do not match");
            return ResponseEntity.badRequest().body(null);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword= bCryptPasswordEncoder.encode(registerDTO.getPassword1());
        User user = userMapper.convertFromRegisterDTO(registerDTO);
        user.setPassword(encodedPassword);
        try{
            userService.save(user);
            LOGGER.info("User registered");
            return ResponseEntity.ok().body(user);
        }catch (Exception e){
            LOGGER.info("Register failed - User already exists");
            return ResponseEntity.badRequest().body(null);
        }
    }

}
