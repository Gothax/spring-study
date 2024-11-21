package com.gothaxcity.userservice.service;

import com.gothaxcity.userservice.domain.UserEntity;
import com.gothaxcity.userservice.dto.UserDto;
import com.gothaxcity.userservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static lombok.AccessLevel.*;
import static org.modelmapper.convention.MatchingStrategies.*;

@Service
@RequiredArgsConstructor(access = PROTECTED)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT);
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPassword("test_encrypted_password");

        userRepository.save(userEntity);

        return mapper.map(userEntity, UserDto.class);
    }


}
