package com.howtographql.hackernews;

import lombok.Data;

@Data
public class User {
    private final String id;
    private final String name;
    private final String email;
    private final String password;

    public User(String name, String email, String password) {
        this(null, name, email, password);
    }

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
