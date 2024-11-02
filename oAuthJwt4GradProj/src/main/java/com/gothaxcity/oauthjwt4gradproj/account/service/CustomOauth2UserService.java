package com.gothaxcity.oauthjwt4gradproj.account.service;

import com.gothaxcity.oauthjwt4gradproj.account.domain.UserEntity;
import com.gothaxcity.oauthjwt4gradproj.account.dto.OAuth2UserInfo;
import com.gothaxcity.oauthjwt4gradproj.account.dto.PrincipalUserDetails;
import com.gothaxcity.oauthjwt4gradproj.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // resource server 회원정보 받아옴
        Map<String, Object> attributes = super.loadUser(userRequest).getAttributes();
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.of(registrationId, attributes);

        // 회원가입 or 로그인
        UserEntity userEntity = getOrSave(oAuth2UserInfo);


        return new PrincipalUserDetails();
    }

    private UserEntity getOrSave(OAuth2UserInfo oAuth2UserInfo) {
        UserEntity userEntity = userRepository.findByEmail(oAuth2UserInfo.email())
                .orElseGet(oAuth2UserInfo::toEntity);
        return userRepository.save(userEntity);
    }
}
