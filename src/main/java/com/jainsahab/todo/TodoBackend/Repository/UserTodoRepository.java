package com.jainsahab.todo.TodoBackend.Repository;

import com.jainsahab.todo.TodoBackend.model.User;
import com.jainsahab.todo.TodoBackend.model.UserTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTodoRepository extends JpaRepository<UserTodo,Integer> {
    List<UserTodo> findByUser(User user);
    void deleteByRequestId(String requestId);
}
