package com.gothaxcity.oauthjwt.service;

import com.gothaxcity.oauthjwt.domain.UserEntity;
import com.gothaxcity.oauthjwt.dto.*;
import com.gothaxcity.oauthjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("oAuth2User = " + oAuth2User);

        // 리소스 서버를 구분 - google / naver
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        // naver
        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }

        // google
        else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }

        else {
            return null;
        }

        // 리소스 서버에서 반환해준 이름이 겹치지 않도록 처리
        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        UserEntity existData = userRepository.findByUsername(username);

        // 처음 로그인 할 경우(db에 존재하지 않을 경우)
        if (existData == null) {
            UserEntity userEntity = new UserEntity(username, oAuth2Response.getName(), oAuth2Response.getEmail(), "ROLE_USER");
            userRepository.save(userEntity);
            UserDto userDto = new UserDto(username, oAuth2Response.getName(), "ROLE_USER");
            return new CustomOAuth2User(userDto);
        } else {

            UserDto userDto = new UserDto(existData.getUsername(), existData.getName(), "ROLE_USER");
            return new CustomOAuth2User(userDto);
        }


    }
}
