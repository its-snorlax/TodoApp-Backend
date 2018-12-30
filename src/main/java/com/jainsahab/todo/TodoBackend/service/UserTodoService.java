package com.jainsahab.todo.TodoBackend.service;

import com.fasterxml.uuid.Generators;
import com.jainsahab.todo.TodoBackend.Repository.UserRepository;
import com.jainsahab.todo.TodoBackend.Repository.UserTodoRepository;
import com.jainsahab.todo.TodoBackend.dto.UserTodoDTO;
import com.jainsahab.todo.TodoBackend.model.User;
import com.jainsahab.todo.TodoBackend.model.UserTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserTodoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTodoRepository  userTodoRepository;

    public void saveTodo(String username,UserTodoDTO userTodoDTO){
        User user = userRepository.findByUsername(username);
        UserTodo userTodo = new UserTodo(uuid(), user, userTodoDTO.getTodoTitle(), userTodoDTO.getTodoBody(), new Date());
        userTodoRepository.save(userTodo);
    }

    private String uuid() {
        UUID uuid = Generators.timeBasedGenerator().generate();
        return uuid.toString();
    }
}
