package com.jainsahab.todo.TodoBackend.Repository;

import com.jainsahab.todo.TodoBackend.model.UserTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTodoRepository extends JpaRepository<UserTodo,Integer> {

}
