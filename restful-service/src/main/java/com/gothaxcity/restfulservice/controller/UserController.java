package com.gothaxcity.restfulservice.controller;

import com.gothaxcity.restfulservice.bean.User;
import com.gothaxcity.restfulservice.dao.UserDaoService;
import com.gothaxcity.restfulservice.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("User with id not found " + id);
        }

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        service.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location)
                .build();
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        User deleteUser = service.deleteById(id);
        if (deleteUser == null) {
            throw new UserNotFoundException("User with id not found " + id);
        }

        return ResponseEntity.noContent().build();
    }

}
