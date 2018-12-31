package com.howtographql.hackernews;

import lombok.Data;

@Data
public class AuthData {
    private String email;
    private String password;

    public AuthData() {
    }

    public AuthData(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
