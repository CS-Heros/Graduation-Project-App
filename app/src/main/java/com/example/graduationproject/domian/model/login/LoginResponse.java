package com.example.graduationproject.domian.model.login;

import com.example.graduationproject.core.BaseResponse;

import java.util.List;

public class LoginResponse extends BaseResponse<LoginResponse.LoginData, LoginResponse.Includes> {

    public LoginResponse(String status, LoginData data, Includes included, List<Error> errors) {
        super(status, data, included, errors);
    }

    public static class LoginData {
        private String token;

        public LoginData(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public static class Includes {}

}