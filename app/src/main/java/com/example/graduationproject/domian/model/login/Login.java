package com.example.graduationproject.domian.model.login;

public class Login {
    private String token;

    public Login(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
