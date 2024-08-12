package com.gothaxcity.springjwt.service;

import com.gothaxcity.springjwt.dto.PrincipalUserDetails;
import com.gothaxcity.springjwt.entity.UserEntity;
import com.gothaxcity.springjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity findUser = userRepository.findByUsername(username);
        if (findUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new PrincipalUserDetails(findUser);
    }
}
