package com.ShopAll.apiShopAll.controllerTest;

import com.ShopAll.apiShopAll.controller.UserController;
import com.ShopAll.apiShopAll.dto.UserDTO;
import com.ShopAll.apiShopAll.entity.User;
import com.ShopAll.apiShopAll.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.xml.transform.Result;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockConstruction;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = UserController.class)
@ExtendWith(MockitoExtension.class)
public class UCTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    private User user;
    private UserDTO userDTO;
    @BeforeEach
    public void init(){
        user = new User("user@domain.com",
                "password",
                "location",
                false);
        userDTO = new UserDTO("user@domain.com",
                "password",
                "location",
                false);
    }
    @Test
    public void createUserSucceed() throws Exception {
        given(userService.postUser(userDTO))
                .willAnswer(ans -> ans.getArgument(1));

        ResultActions response = mockMvc
                .perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createUserFailed() throws Exception{
        userDTO = new UserDTO("user",
                "pass",
                "",
                false);
        given(userService.postUser(userDTO))
                .willAnswer(ans -> ans.getArgument(1));

        ResultActions response = mockMvc
                .perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void updateUserSucceed() throws Exception{
        String email = userDTO.getEmail();
        userDTO.setEmail("dummy@dumb.com");
        userDTO.setLocation("España, Pamplona");

        given(userService.putUser(email, userDTO))
                .willAnswer(ans -> ans.getArgument(1));

        ResultActions response = mockMvc
                .perform(put("/users/update/{email}", email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void updateUserFailedBecauseBody() throws Exception{
        String email = userDTO.getEmail();
        userDTO.setEmail("dummy");
        userDTO.setLocation("");

        given(userService.putUser(email, userDTO))
                .willAnswer(ans -> ans.getArgument(1));

        ResultActions response = mockMvc
                .perform(put("/users/update/{email}", email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    //-----under test----//
    /*@Test
    public void updateUserFailedBecauseUnknownEmail() throws Exception{
        String email = "jhonromero@doomcreators.com";
        userDTO.setEmail("dummy@dumb.com");
        userDTO.setLocation("España, Pamplona");

        given(userService.putUser(email, userDTO))
                .willAnswer(ans -> ans.getArgument(1));

        ResultActions response = mockMvc
                .perform(put("/users/update/{email}", email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());
    }*/
}
