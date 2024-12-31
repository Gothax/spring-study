package com.gothaxcity.userservice.service;

import com.gothaxcity.userservice.client.OrderServiceClient;
import com.gothaxcity.userservice.domain.UserEntity;
import com.gothaxcity.userservice.dto.UserDto;
import com.gothaxcity.userservice.repository.UserRepository;
import com.gothaxcity.userservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;
import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Service
@RequiredArgsConstructor(access = PROTECTED)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final Environment env;
    private final OrderServiceClient orderServiceClient;
//    private final RestTemplate restTemplate;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT);
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(userEntity);

        return mapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserById(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);


//        List<ResponseOrder> orders = new ArrayList<>();
        /*
         * Using RestTemplate to call Order Service
         */
//        String orderUrl = String.format(env.getProperty("order_service.url"), userId);
//        ResponseEntity<List<ResponseOrder>> orderListResponse =
//                restTemplate.exchange(orderUrl, GET, null,
//                                      new ParameterizedTypeReference<List<ResponseOrder>>() {
//                });

//        List<ResponseOrder> orders = orderListResponse.getBody();
//        userDto.setOrders(orders);


        /*
         * Using Feign Client to call Order Service
         */
        List<ResponseOrder> responseOrderList = orderServiceClient.getOrders(userId);
        userDto.setOrders(responseOrderList);

        return userDto;
    }


    @Override
    public Iterable<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }


}
