package com.example.foodpanda;

import com.example.foodpanda.controller.AdminController;
import com.example.foodpanda.controller.RegisterController;
import com.example.foodpanda.dto.RegisterDTO;
import com.example.foodpanda.entity.*;
import com.example.foodpanda.repository.FoodRepository;
import com.example.foodpanda.repository.RestaurantRepository;
import com.example.foodpanda.repository.UserRepository;
import com.example.foodpanda.security.jwt.JwtAuthenticationFilter;
import com.example.foodpanda.security.jwt.JwtService;
import com.example.foodpanda.service.FoodService;
import com.example.foodpanda.service.RestaurantService;
import com.example.foodpanda.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

/*
@RunWith(SpringRunner.class)
@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Mock
  private UserRepository userRepository;

  UserService userService;

  @BeforeEach
  public void init(){
    userService = new UserService(userRepository);
  }

  @Test
  public void registerSuccess() throws Exception {
    RegisterDTO registerDTO = new RegisterDTO("test","pass1","pass1","street","test@gmail.com");
    User user = new User();
    when(userService.save(any())).thenReturn(user);

    mockMvc.perform(MockMvcRequestBuilders.post("/register")
            .content(asJsonString(registerDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}*/
