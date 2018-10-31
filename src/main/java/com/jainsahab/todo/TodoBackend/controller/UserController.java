package com.jainsahab.todo.TodoBackend.controller;

import com.jainsahab.todo.TodoBackend.dto.UserInfo;
import com.jainsahab.todo.TodoBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.HttpStatus.CREATED;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity signup(@RequestBody UserInfo userInfo){
        return userService.signup(userInfo) ?
                ResponseEntity.status(CREATED).build() :
                ResponseEntity.badRequest().build();
    }
}
