package com.ShopAll.apiShopAll.controllerTest;

import com.ShopAll.apiShopAll.controller.UserController;
import com.ShopAll.apiShopAll.dto.UserDTO;
import com.ShopAll.apiShopAll.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;

@WebMvcTest(UserController.class)
public class UserTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUserByEmailSucceed() throws Exception{
        //provide a user dto that already exists on the database
        UserDTO userDTO = new UserDTO(
                "admin@domian.com",
                "string",
                "string",
                true
        );
        given(userService.getUserByEmail(userDTO.getEmail())).willReturn(new ResponseEntity<>(userDTO, HttpStatus.OK));
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{email}", userDTO.getEmail()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
