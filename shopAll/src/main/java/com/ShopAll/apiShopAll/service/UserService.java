package com.ShopAll.apiShopAll.service;

import com.ShopAll.apiShopAll.dto.UserDTO;
import com.ShopAll.apiShopAll.entity.User;
import com.ShopAll.apiShopAll.exception.RequestException;
import com.ShopAll.apiShopAll.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> postUser(UserDTO newUser){
        if(userRepository.existsByEmail(newUser.getEmail())){throw new RequestException("Email already in use");}
            User user = new User(
                    newUser.getEmail(),
                    newUser.getPassword(),
                    newUser.getLocation(),
                    newUser.getIs_company()
            );
        return new ResponseEntity<>( userRepository.save(user), HttpStatus.OK);
    }
    public ResponseEntity<Object> getUserByEmail(String email) {
        if(!userRepository.existsByEmail(email)){throw new RequestException("Make sure you're providing a correct email");}
        UserDTO userDTO = userRepository.findByEmail(email)
                .map(user -> new UserDTO(
                        user.getEmail(),
                        user.getPassword(),
                        user.getLocation(),
                        user.isCompany()
                ))
                .orElse(null);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(
                        user.getEmail(),
                        user.getLocation(),
                        user.getPassword(),
                        user.isCompany())).
                collect(Collectors.toList());
    }
    public ResponseEntity<Object> putUser( String email,UserDTO newUser){
        User user = userRepository.findByEmail(email).orElse(null);
        if(user == null) {throw new RequestException("Email does not exist");}
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setLocation(newUser.getLocation());
        user.setCompany(newUser.getIs_company());
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);

    }
    public HttpStatus deleteUser(String email) {
        if(!userRepository.existsByEmail(email)){throw new RequestException("Email does not exist");}
        userRepository.deleteByEmail(email);
        return HttpStatus.OK;
    }
}
