package com.jainsahab.todo.TodoBackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_todo")
@NoArgsConstructor
public class UserTodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Getter
    @Column(name = "request_id")
    private String requestId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false)
    @Getter
    private String todoTitle;

    @Column(name = "description", nullable = false)
    @Getter
    private String todoBody;

    @Column(name = "created_at")
    @Getter
    private Date createdAt;

    public UserTodo(String requestId, User user, String todoTitle, String todoBody, Date createdAt) {
        this.requestId = requestId;
        this.user = user;
        this.todoTitle = todoTitle;
        this.todoBody = todoBody;
        this.createdAt = createdAt;
    }
}

