package com.ShopAll.apiShopAll.controller;

import com.ShopAll.apiShopAll.dto.UserDTO;
import com.ShopAll.apiShopAll.entity.Product;
import com.ShopAll.apiShopAll.entity.User;
import com.ShopAll.apiShopAll.exception.RequestException;
import com.ShopAll.apiShopAll.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class UserController {
    @Autowired
    private UserService userService;
//------*
    @Operation(summary = "Create a new user")
    @PostMapping("/create")
    public ResponseEntity<Object> postUser(@Valid @RequestBody UserDTO userDTO){
        return userService.postUser(userDTO);
    }

    @Operation(summary = "Get a user by email")
    @GetMapping("/{email}")
    public ResponseEntity<Object> getUser(@PathVariable String email){
        return userService.getUserByEmail(email);
    }
//------*
    @Operation(summary = "Get all users")
    @GetMapping("/getAllUsers")
    public List<UserDTO> getAll(){return userService.getAllUsers();}

    @Operation(summary = "Update a user")
    @ApiResponse(responseCode = "400",
            description = "\"Bad request\"\nAsegurese de que está realizando una consulta en el formato adecuado.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Product.class),
                    examples = @ExampleObject(value = "{\n" +
                            "  \"email\": \"string\",\n" +
                            "  \"password\": \"string\",\n" +
                            "  \"location\": \"string\",\n" +
                            "  \"company\": true,\n" +
                            "  \"productList\": null\n" +
                            "}")
            ))
    @PutMapping("/update/{email}")
    public ResponseEntity<Object> updateUser(@PathVariable String email, @Valid@RequestBody UserDTO userDTO){
        //System.out.println(email);
        /*if(!userService.checkUser(email)){
            System.out.println("entré al if");
            throw new RequestException("Email not found :(");
        }*/
        return userService.putUser(email, userDTO);
        //return HttpStatus.OK;
        /*try{

        }catch (RequestException ex){
            return HttpStatus.NOT_FOUND;
        }catch (RuntimeException ex){
            return  HttpStatus.BAD_REQUEST;
        }*/
        // {return userService.putUser(id, userDTO);}
    }


    @Transactional
    @Operation(summary = "Delete a user")
    @DeleteMapping("/delete/{email}")
    public HttpStatus deleteUser(@PathVariable String email){
        /*if(!userService.checkUser(email)){
            throw new RequestException("Email not found :(");
        }*/
        return userService.deleteUser(email);
        //return HttpStatus.OK;
    }
}
