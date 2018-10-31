package com.jainsahab.todo.TodoBackend.service;

import com.jainsahab.todo.TodoBackend.Repository.UserRepository;
import com.jainsahab.todo.TodoBackend.dto.UserInfo;
import com.jainsahab.todo.TodoBackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public boolean signup(UserInfo userInfo) {
        if (userRepository.findByUsername(userInfo.getUsername()) != null) {
            return false;
        }
        String encodedPassword = passwordEncoder.encode(userInfo.getPassword());
        userRepository.save(new User(userInfo.getUsername(), encodedPassword));
        return true;
    }
}
