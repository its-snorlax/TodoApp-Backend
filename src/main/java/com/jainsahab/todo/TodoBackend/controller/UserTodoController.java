package com.jainsahab.todo.TodoBackend.controller;

import com.jainsahab.todo.TodoBackend.dto.UserTodoDTO;
import com.jainsahab.todo.TodoBackend.service.UserService;
import com.jainsahab.todo.TodoBackend.service.UserTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.jainsahab.todo.TodoBackend.config.security.JWTAuthenticationFilter.JWT_TOKEN;

@Controller
public class UserTodoController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTodoService userTodoService;

    @PostMapping("/Todos")
    @ResponseBody
    public ResponseEntity createTodos(@RequestBody UserTodoDTO userTodoDTO,
                                     @RequestHeader(JWT_TOKEN) String jwtToken){
        String userName = userService.findUserNameByToken(jwtToken);
        userTodoService.saveTodo(userName,userTodoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
