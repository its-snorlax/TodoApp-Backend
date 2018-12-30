package com.jainsahab.todo.TodoBackend.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jainsahab.todo.TodoBackend.AppProperties;
import com.jainsahab.todo.TodoBackend.dto.UserInfo;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;


public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private AuthenticationManager authenticationManager;
    private AppProperties appProperties;
    public static final String JWT_TOKEN = "jwt_token";
    public static final String HEADER_PREFIX = "Bearer ";

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AppProperties appProperties) {
        super(new AntPathRequestMatcher("/login", "POST"));
        this.authenticationManager = authenticationManager;
        this.appProperties =  appProperties;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        try {
            UserInfo creds = new ObjectMapper()
                    .readValue(request.getInputStream(), UserInfo.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        System.out.println("authentication failed");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws UnsupportedEncodingException {
        String username = ((User) auth.getPrincipal()).getUsername();
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, appProperties.getSecret().getBytes("UTF-8"))
                .compact();
        res.addHeader(JWT_TOKEN, HEADER_PREFIX + token);
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
    }
}

