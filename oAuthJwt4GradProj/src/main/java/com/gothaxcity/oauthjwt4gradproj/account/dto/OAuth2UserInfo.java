package com.gothaxcity.oauthjwt4gradproj.account.dto;

import com.gothaxcity.oauthjwt4gradproj.account.exception.AuthException;
import lombok.Builder;

import java.util.Map;

import static com.gothaxcity.oauthjwt4gradproj.exception.ErrorCode.ILLEGAL_REGISTRATION_ID;

@Builder
public record OAuth2UserInfo(
        String name,
        String email,
        String imageUrl
) {
    public static OAuth2UserInfo of(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase("google")) {
            return ofGoogle(attributes);
        }
        // TODO: GOOGLE OAUTH 구현
//        else if (registrationId.equalsIgnoreCase("apple")) {
//            return ofApple(attributes);
//        }
        throw new AuthException(ILLEGAL_REGISTRATION_ID);
    }

    private static OAuth2UserInfo ofGoogle(Map<String, Object> attributes) {
        return OAuth2UserInfo.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .imageUrl((String) attributes.get("picture"))
                .build();
    }
}
