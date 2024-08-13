package com.gothaxcity.springjwt.controller;

import com.gothaxcity.springjwt.jwt.JwtProvider;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gothaxcity.springjwt.constant.Constants.ACCESS_EXPIRATION_MS;

@RestController
@RequiredArgsConstructor
public class ReissueController {

    private final JwtProvider jwtProvider;

    // TODO SERVICE 계층으로 비즈니스 로직을 분리

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {

        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
            }
        }
        if (refresh == null) {
            return new ResponseEntity<>("refresh token is null", HttpStatus.BAD_REQUEST);
        }

        try {
            jwtProvider.isTokenExpired(refresh);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>("refresh token is expired", HttpStatus.UNAUTHORIZED);
        }

        String category = jwtProvider.getCategory(refresh);
        if (!category.equals("refresh")) {
            return new ResponseEntity<>("refresh token is invalid", HttpStatus.UNAUTHORIZED);
        }

        String newAccessToken = jwtProvider.createJwt("access", jwtProvider.getUsername(refresh), jwtProvider.getRole(refresh), ACCESS_EXPIRATION_MS);

        response.setHeader("access", newAccessToken);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
