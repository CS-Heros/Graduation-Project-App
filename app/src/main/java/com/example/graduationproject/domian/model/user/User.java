package com.example.graduationproject.domian.model.user;

import com.google.gson.annotations.SerializedName;

public class User {
    private long id;
    private String avatar;
    private String name;
    private String email;
    @SerializedName("allowed_to_save_images")
    private String allowToSaveImage;

    public User(long id, String avatar, String name, String email, String allowToSaveImage) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.email = email;
        this.allowToSaveImage = allowToSaveImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getAllowToSaveImage() {
        return allowToSaveImage;
    }

    public void setAllowToSaveImage(String allowToSaveImage) {
        this.allowToSaveImage = allowToSaveImage;
    }
}
