package com.ShopAll.apiShopAll.service;

import com.ShopAll.apiShopAll.dto.CategoryDTO;
import com.ShopAll.apiShopAll.dto.DetailDTO;
import com.ShopAll.apiShopAll.dto.ProductDTO;
import com.ShopAll.apiShopAll.entity.Category;
import com.ShopAll.apiShopAll.entity.Detail;
import com.ShopAll.apiShopAll.entity.Product;
import com.ShopAll.apiShopAll.entity.User;
import com.ShopAll.apiShopAll.exception.RequestException;
import com.ShopAll.apiShopAll.repository.ProductRepository;
import com.ShopAll.apiShopAll.repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> addProduct(String email, Set<ProductDTO> setProduct) {
        User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException :: new);
        user.getProductSet().addAll(
                setProduct.stream().map(productDTO -> new Product(
                        productDTO.getName(),
                        productDTO.getQuantityInStock(),
                        productDTO.getPrice()
                )).collect(Collectors.toSet())
        );
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    public ResponseEntity<Object> getProduct(String email){
        User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        return new ResponseEntity<>(
                user.getProductSet().stream().map(product -> new ProductDTO(
                        product.getName(),
                        product.getQuantityInStock(),
                        product.getPrice()
                )).collect(Collectors.toSet()), HttpStatus.OK
        );
    }

    public ResponseEntity<Object> updateProduct(String userEmail, String productName, ProductDTO newProduct) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(IllegalCallerException::new);
        user.setProductSet(user.getProductSet().stream().map(p -> p.getName().equals(productName) ? new Product(
                newProduct.getName(),
                newProduct.getQuantityInStock(),
                newProduct.getPrice()) : p
        ).collect(Collectors.toSet()));
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    public HttpStatus deleteProduct(String name) {
        Product product = productRepository.findByName(name).orElseThrow(IllegalArgumentException :: new);
        productRepository.delete(product);
        return HttpStatus.OK;

    }


}
