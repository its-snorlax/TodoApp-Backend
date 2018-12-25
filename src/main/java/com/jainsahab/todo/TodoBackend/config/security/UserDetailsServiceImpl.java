package com.jainsahab.todo.TodoBackend.config.security;

import com.jainsahab.todo.TodoBackend.Repository.UserRepository;
import com.jainsahab.todo.TodoBackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        System.out.println("database user: " + user);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return new com.jainsahab.todo.TodoBackend.config.security.User(user.getUsername(),user.getPassword());
    }
}
