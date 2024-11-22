package com.gothaxcity.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gothaxcity.userservice.vo.RequestLogin;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;


@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserDetailsService userDetailsService;
    private final Environment env;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                UserDetailsService userDetailsService,
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
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    }


}
