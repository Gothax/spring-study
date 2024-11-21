package com.gothaxcity.userservice.controller;

import com.gothaxcity.userservice.dto.UserDto;
import com.gothaxcity.userservice.service.UserService;
import com.gothaxcity.userservice.vo.Greeting;
import com.gothaxcity.userservice.vo.RequestUser;
import com.gothaxcity.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.modelmapper.convention.MatchingStrategies.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Environment env;


    @Autowired
    private Greeting greeting;

    @GetMapping("/health-check")
    public String welcome() {
        return "User Service is up and running";
    }


    @GetMapping("/welcome")
    public String welcomeUser() {
        return env.getProperty("greeting.message");
    }

    @GetMapping("/welcome2")
    public String welcomeUser2() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(CREATED).body(responseUser);
    }

}
