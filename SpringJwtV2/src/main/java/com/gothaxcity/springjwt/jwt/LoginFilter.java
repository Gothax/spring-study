package com.gothaxcity.springjwt.jwt;

import com.gothaxcity.springjwt.dto.PrincipalUserDetails;
import com.gothaxcity.springjwt.entity.RefreshEntity;
import com.gothaxcity.springjwt.repository.RefreshRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

import static com.gothaxcity.springjwt.constant.Constants.*;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshRepository refreshRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        // authentication manager에게 인증을 받기 위한 dto라고 생각하면 될듯
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, null);
        return authenticationManager.authenticate(authenticationToken);
    }

    // (LoginSuccessHandler)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String username = ((PrincipalUserDetails) authResult.getPrincipal()).getUsername();
        String role = authResult.getAuthorities().iterator().next().getAuthority();

        // 토큰 생성
        String accessToken = jwtProvider.createJwt("access", username, role, ACCESS_EXPIRATION_MS);
        String refreshToken = jwtProvider.createJwt("refresh", username, role, REFRESH_EXPIRATION_MS);

        // refresh 토큰 저장
        addRefreshEntity(username, refreshToken, REFRESH_EXPIRATION_MS);

        // 응답 설정
        response.addHeader("access", accessToken);
        response.addCookie(createCookie("refresh", refreshToken));
        response.setStatus(HttpStatus.OK.value());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
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
