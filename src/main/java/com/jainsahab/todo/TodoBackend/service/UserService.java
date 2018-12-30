package com.jainsahab.todo.TodoBackend.service;

import com.jainsahab.todo.TodoBackend.AppProperties;
import com.jainsahab.todo.TodoBackend.Repository.UserRepository;
import com.jainsahab.todo.TodoBackend.dto.UserInfo;
import com.jainsahab.todo.TodoBackend.model.User;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.jainsahab.todo.TodoBackend.config.security.JWTAuthenticationFilter.HEADER_PREFIX;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppProperties appProperties;

    public boolean signup(UserInfo userInfo) {
        if (userRepository.findByUsername(userInfo.getUsername()) != null) {
            return false;
        }
        String encodedPassword = passwordEncoder.encode(userInfo.getPassword());
        userRepository.save(new User(userInfo.getUsername(), encodedPassword));
        return true;
    }

     public String findUserNameByToken(String token){
        return Jwts.parser()
                 .setSigningKey(appProperties.getSecret().getBytes())
                 .parseClaimsJws(token.replace(HEADER_PREFIX,""))
                 .getBody()
                 .getSubject();
     }
}
