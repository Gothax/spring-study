package com.gothaxcity.springjwt.jwt;

import com.gothaxcity.springjwt.dto.PrincipalUserDetails;
import com.gothaxcity.springjwt.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.gothaxcity.springjwt.constant.Constants.TOKEN_PREFIX;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        // authorization header 검증
        if (authorization == null || !authorization.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.substring(TOKEN_PREFIX.length());

        // 소멸시간 검증
        if (jwtProvider.isTokenExpired(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 토근이 정상적이라면, 스프링 세큐리티 세션에 사용자를 등록
        SecurityContextHolder.getContext().setAuthentication(jwtProvider.getAuthentication(token));
        filterChain.doFilter(request, response);
    }
}
