package com.informatorio.shoppingcart.controller;

import com.informatorio.shoppingcart.entity.User;
import com.informatorio.shoppingcart.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        Optional<User> userFound = userRepository.findById(id);
        if (userFound.isPresent()) {
            return new ResponseEntity(userFound, HttpStatus.OK);
        }
        return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user){
        return new ResponseEntity(userRepository.save(user), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateUserAddress(@PathVariable Long id, @RequestBody User user){
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()){
            User existingUser = foundUser.get();
            existingUser.setAddress(user.getAddress());
            return new ResponseEntity(userRepository.save(existingUser), HttpStatus.OK);
        }
        return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            userRepository.deleteById(id);
            return new ResponseEntity("User deleted", HttpStatus.OK);
        }
        return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
    }
}
