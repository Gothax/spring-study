package com.gothaxcity.secondservice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/second-service")
public class Controller {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Second Service!";
    }


    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
        log.info(header);
        return "Hello from the Second Service!";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, This is a message from Second Service";
    }

}
