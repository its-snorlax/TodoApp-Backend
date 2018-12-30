package com.jainsahab.todo.TodoBackend.dto;

import com.jainsahab.todo.TodoBackend.model.UserTodo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
@NoArgsConstructor
public class UserTodoDTO {
    Date createdAt;
    String requestId;
    String todoBody;
    String todoTitle;

    public UserTodoDTO(UserTodo userTodo) {
        createdAt = userTodo.getCreatedAt();
        requestId = userTodo.getRequestId();
        todoBody = userTodo.getTodoBody();
        todoTitle =  userTodo.getTodoTitle();

    }
}
