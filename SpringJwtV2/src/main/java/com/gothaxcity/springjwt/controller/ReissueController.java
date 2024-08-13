package com.gothaxcity.springjwt.controller;

import com.gothaxcity.springjwt.entity.RefreshEntity;
import com.gothaxcity.springjwt.jwt.JwtProvider;
import com.gothaxcity.springjwt.repository.RefreshRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.gothaxcity.springjwt.constant.Constants.ACCESS_EXPIRATION_MS;
import static com.gothaxcity.springjwt.constant.Constants.REFRESH_EXPIRATION_MS;

@RestController
@RequiredArgsConstructor
public class ReissueController {

    private final JwtProvider jwtProvider;
    private final RefreshRepository refreshRepository;

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

        String username = jwtProvider.getUsername(refresh);
        String role = jwtProvider.getRole(refresh);
        String newAccessToken = jwtProvider.createJwt("access", username, role, ACCESS_EXPIRATION_MS);
        String newRefreshToken = jwtProvider.createJwt("refresh", username, role, REFRESH_EXPIRATION_MS);

        // refresh token 저장, 기존 refresh token은 삭제
        refreshRepository.deleteByRefreshToken(refresh);
        addRefreshEntity(username, role, REFRESH_EXPIRATION_MS);

        // 응답
        response.setHeader("access", newAccessToken);
        response.addCookie(createCookie("refresh", newRefreshToken));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*24);
//        cookie.setPath("/");
        // https에서만 동작하도록
//        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        return cookie;
    }


    private void addRefreshEntity(String username, String refreshToken, Long expiration) {
        RefreshEntity refreshEntity = new RefreshEntity(username, refreshToken, new Date(System.currentTimeMillis() + expiration).toString());
        refreshRepository.save(refreshEntity);
    }
}
