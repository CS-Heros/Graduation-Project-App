package com.example.graduationproject.domian.model.auth;

public class Auth {
    private String token;

    public Auth(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

// "data": {
//        "token": "27|NuhqeJfP2r8YVQ2DvWvMjrfbhBSu7lnJTDqRPRYx",
//        "relationships": [],
//        "included": []
//    },
