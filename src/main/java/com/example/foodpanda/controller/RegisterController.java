package com.example.foodpanda.controller;

import com.example.foodpanda.dto.RegisterDTO;
import com.example.foodpanda.dto.UserMapper;
import com.example.foodpanda.entity.User;
import com.example.foodpanda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class RegisterController {

    @Autowired
    private final UserService userService;
    private final UserMapper userMapper = new UserMapper();

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value ="/register",consumes = "application/json")
    public ResponseEntity<User> registerUser(@RequestBody RegisterDTO registerDTO){
        if(registerDTO.getAddress().equals("")||registerDTO.getEmail().equals("")||registerDTO.getPassword1().equals("")||registerDTO.getPassword2().equals("")||registerDTO.getUsername().equals("")){
            System.out.println("All fields must be completed");
            return ResponseEntity.badRequest().body(null);
        }
        if(!registerDTO.getPassword1().equals(registerDTO.getPassword2())){
            System.out.println("Passwords must be the same");
            return ResponseEntity.badRequest().body(null);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword= bCryptPasswordEncoder.encode(registerDTO.getPassword1());
        User user = userMapper.convertFromRegisterDTO(registerDTO);
        user.setPassword(encodedPassword);
        try{
            userService.save(user);
            return ResponseEntity.ok().body(user);
        }catch (Exception e){
            System.out.println("User already exists");
            return ResponseEntity.badRequest().body(null);
        }
    }

}
