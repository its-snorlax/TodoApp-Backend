package com.jainsahab.todo.TodoBackend.config.security;

import com.jainsahab.todo.TodoBackend.AppProperties;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.jainsahab.todo.TodoBackend.config.security.JWTAuthenticationFilter.HEADER_PREFIX;
import static com.jainsahab.todo.TodoBackend.config.security.JWTAuthenticationFilter.JWT_TOKEN;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private final AppProperties appProperties;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, AppProperties appProperties) {
        super(authenticationManager);
        this.appProperties = appProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JWT_TOKEN);
        if (header == null || !header.startsWith(HEADER_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JWT_TOKEN);
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(appProperties.getSecret().getBytes())
                    .parseClaimsJws(token.replace(HEADER_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}
