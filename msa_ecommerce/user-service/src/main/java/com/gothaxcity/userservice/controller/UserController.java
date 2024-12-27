package com.gothaxcity.userservice.controller;

import com.gothaxcity.userservice.domain.UserEntity;
import com.gothaxcity.userservice.dto.UserDto;
import com.gothaxcity.userservice.service.UserService;
import com.gothaxcity.userservice.vo.Greeting;
import com.gothaxcity.userservice.vo.RequestUser;
import com.gothaxcity.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.modelmapper.convention.MatchingStrategies.STRICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Environment env;
    private final Greeting greeting;

    @GetMapping("/health_check")
    public String health_check() {
        return String.format("It's Working in User Service" +
                ", port(local.server.port)=" + env.getProperty("local.server.port") +
                ", port(server.port)=" + env.getProperty("server.port") +
                ", token secret = " + env.getProperty("token.secret") +
                ", token expiration time = " + env.getProperty("token.expiration-time"));
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

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getAllUser() {
        Iterable<UserEntity> allUsers = userService.getAllUsers();

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT);
        List<ResponseUser> result = new ArrayList<>();

        allUsers.forEach(user -> {
            result.add(mapper.map(user, ResponseUser.class));
        });

        return ResponseEntity.status(OK).body(result);
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUserById(userId);
        ResponseUser responseUser = new ModelMapper().map(userDto, ResponseUser.class);

        return ResponseEntity.status(OK).body(responseUser);
    }
}
