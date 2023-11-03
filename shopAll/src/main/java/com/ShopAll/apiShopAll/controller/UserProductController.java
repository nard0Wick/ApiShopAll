package com.ShopAll.apiShopAll.controller;

import com.ShopAll.apiShopAll.dto.UserDTO;
import com.ShopAll.apiShopAll.entity.Category;
import com.ShopAll.apiShopAll.entity.Product;
import com.ShopAll.apiShopAll.entity.User;
import com.ShopAll.apiShopAll.service.CategoryService;
import com.ShopAll.apiShopAll.service.ProductService;
import com.ShopAll.apiShopAll.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users_products")
@Tag(name = "Users_Products")
public class UserProductController {

    private UserService userService;
    private ProductService productService;
    private CategoryService categoryService;
    @Autowired
    public UserProductController(UserService userService, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

}
