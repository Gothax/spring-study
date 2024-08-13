package com.gothaxcity.springjwt.service;

import com.gothaxcity.springjwt.dto.JoinDto;
import com.gothaxcity.springjwt.entity.UserEntity;
import com.gothaxcity.springjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDto joinDto) {

        String username = joinDto.username();
        String password = joinDto.password();

        Boolean exists = userRepository.existsByUsername(username);
        if (exists) {
            return;
        }
        UserEntity user = new UserEntity(username, bCryptPasswordEncoder.encode(password), "ROLE_ADMIN");
        userRepository.save(user);
    }
}
