package com.gothaxcity.oauthjwt4gradproj.account.dto;

import com.gothaxcity.oauthjwt4gradproj.account.domain.UserEntity;

public record PrincipalUserDetails(
        UserEntity userEntity

) {
}
