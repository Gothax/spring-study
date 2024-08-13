package com.gothaxcity.springjwt.jwt;

import com.gothaxcity.springjwt.dto.PrincipalUserDetails;
import com.gothaxcity.springjwt.entity.UserEntity;
import io.jsonwebtoken.ExpiredJwtException;
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
        String accessToken = request.getHeader("access");

        // authorization header 검증
        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // access token이 만료 되었는지 검증
        try {
            jwtProvider.isTokenExpired(accessToken);
        } catch (ExpiredJwtException e) {
            response.getWriter().print("access token expired");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String category = jwtProvider.getCategory(accessToken);
        if (!category.equals("access")) {
            response.getWriter().print("invalid token");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 토근이 정상적이라면, 스프링 세큐리티 세션에 사용자를 등록
        SecurityContextHolder.getContext().setAuthentication(jwtProvider.getAuthentication(accessToken));
        filterChain.doFilter(request, response);
    }
}
