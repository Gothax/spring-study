package com.gothaxcity.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gothaxcity.userservice.dto.UserDto;
import com.gothaxcity.userservice.service.CustomUserDetailsService;
import com.gothaxcity.userservice.vo.RequestLogin;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;


@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final CustomUserDetailsService userDetailsService;
    private final Environment env;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                CustomUserDetailsService userDetailsService,
                                Environment env) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.env = env;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            RequestLogin credentials = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getEmail(),
                                                            credentials.getPassword(),
                                                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String username = ((User)authResult.getPrincipal()).getUsername();

        UserDto userDetails = userDetailsService.getUserDetailsByEmail(username);
        SecretKey secretKey = getSecretKey();

        String token = Jwts.builder()
                .subject(userDetails.getUserId())
                .expiration(new Date(System.currentTimeMillis() +
                                             Long.parseLong(env.getProperty("token.expiration-time"))))
                .signWith(secretKey)
                .compact();

        response.addHeader("token", token);
        response.addHeader("userId", userDetails.getUserId());

    }

    private SecretKey getSecretKey() {
        byte[] envSecretBytes = Objects.requireNonNull(env.getProperty("token.secret")).getBytes();
        return new SecretKeySpec(envSecretBytes, HS512.getJcaName());
    }


}
