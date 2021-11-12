package com.example.graduationproject.domian.model.user;

public class User {
    private String avatar;
    private String name;
    private String email;

    public User(String avatar, String name, String email) {
        this.avatar = avatar;
        this.name = name;
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
