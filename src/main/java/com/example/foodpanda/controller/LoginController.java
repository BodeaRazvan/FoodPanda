package com.example.foodpanda.controller;

import com.example.foodpanda.dto.LoginDTO;
import com.example.foodpanda.dto.TokenDTO;
import com.example.foodpanda.entity.User;
import com.example.foodpanda.logger.MyLogger;
import com.example.foodpanda.security.jwt.AuthenticationService;
import com.example.foodpanda.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
public class LoginController {
    private final AuthenticationService authenticationService;
    private static final Logger LOGGER = MyLogger.getInstance();

    private final UserService userService;

    @Autowired
    public LoginController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    public static User getCurrentUser() {
        return AuthenticationService.getAuthed();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO){
        LOGGER.info("Login controller request login user");
        String token = authenticationService.login(loginDTO.getUsername(), loginDTO.getPassword());
        if(token == null || token.isEmpty()){
            LOGGER.info("Login controller login user failed");
            return ResponseEntity.badRequest().build();
        }
        User user = userService.findByUsername(loginDTO.getUsername());

        return  new ResponseEntity<>(new TokenDTO(user, token),HttpStatus.OK);
    }

}
