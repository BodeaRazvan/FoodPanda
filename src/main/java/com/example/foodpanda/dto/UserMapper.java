package com.example.foodpanda.dto;

import com.example.foodpanda.entity.User;

public class UserMapper {
    public User convertFromLoginDTO(LoginDTO loginDTO){
        User user = new User();
        user.setAddress("");
        user.setEmail("");
        user.setOrder(null);
        user.setUsername(loginDTO.getUsername());
        user.setPassword(loginDTO.getPassword());
        user.setRestaurant(null);
        user.setRole("");
        return user;
    }

    public LoginDTO convertToLoginDTO(User user){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPassword(user.getPassword());
        loginDTO.setUsername(user.getUsername());
        return loginDTO;
    }

    public User convertFromRegisterDTO(RegisterDTO registerDTO){
        User user = new User();
        user.setAddress(registerDTO.getAddress());
        user.setEmail(registerDTO.getEmail());
        user.setOrder(null);
        user.setUsername(registerDTO.getUsername());
        user.setPassword(registerDTO.getPassword1());
        user.setRestaurant(null);
        user.setRole("USER");
        return user;
    }

    public RegisterDTO convertToRegisterDTO(User user){
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setAddress(user.getAddress());
        registerDTO.setEmail(user.getEmail());
        registerDTO.setPassword1(user.getPassword());
        registerDTO.setPassword2(user.getPassword());
        registerDTO.setUsername(user.getUsername());
        return registerDTO;
    }
}
