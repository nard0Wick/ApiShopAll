package com.ShopAll.apiShopAll.controller;


import com.ShopAll.apiShopAll.dto.ProductDTO;
import com.ShopAll.apiShopAll.entity.Product;
import com.ShopAll.apiShopAll.entity.User;
import com.ShopAll.apiShopAll.repository.ProductRepository;
import com.ShopAll.apiShopAll.repository.UserRepository;
import com.ShopAll.apiShopAll.service.ProductService;
import com.ShopAll.apiShopAll.service.UserService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/products")
@Tag(name = "Products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "Add some products")
    @PostMapping("/addProduct/{email}")
    public ResponseEntity<Object> addProduct(@PathVariable String email, @RequestBody Set<ProductDTO> setProduct) {
        return productService.addProduct(email, setProduct);
    }

    @Operation(summary = "update a product")
    @PutMapping("/update/{userEmail}/{productName}")
    public ResponseEntity<Object> updateProduct(@PathVariable String userEmail,
                                                @PathVariable String productName,
                                                @RequestBody ProductDTO newProduct) {
        return productService.updateProduct(userEmail, productName, newProduct);
    }

    @Operation(summary = "Get a set of products according to user's email provided")
    @GetMapping("/get/{email}")
    public ResponseEntity<Object> getProductSet(@PathVariable String email){
        return productService.getProduct(email);
    }

    @Operation(summary = "delete a product")
    @DeleteMapping("/delete/{productName}")
    public HttpStatus deleteProduct(@PathVariable String productName) {
        return productService.deleteProduct(productName);
    }
}
