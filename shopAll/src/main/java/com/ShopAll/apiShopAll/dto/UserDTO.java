package com.ShopAll.apiShopAll.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
@AllArgsConstructor
@Getter
@Setter
public class UserDTO{

    @NotEmpty(message = "\'email\' cannot be blank")
    @Email(message = "\'email\' should be valid \'example@domain.com\'")
    private String email;
    @NotNull(message = "\'password\'cannot be null type")
    @Size(min = 6, message = "\'password\' should be at least 6 characters long")
    private String password;
    @NotEmpty(message = "\'location\' cannot be blank")
    private String location;
    @NotNull(message = "\'is_company\' cannot be null type")
    private Boolean is_company;

    /*public UserDTO(String email, String password, String location, Boolean is_company) {
        this.email = email;
        this.password = password;
        this.location = location;
        this.is_company = is_company;
    }*/
}
