package com.gothaxcity.springjwt.controller;

import com.gothaxcity.springjwt.dto.JoinDto;
import com.gothaxcity.springjwt.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public String join(JoinDto joinDto){
        joinService.joinProcess(joinDto);
        return "ok";
    }
}
