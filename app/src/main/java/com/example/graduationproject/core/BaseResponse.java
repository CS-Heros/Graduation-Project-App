package com.example.graduationproject.core;

import java.util.List;

public class BaseResponse {
    private String type;
    private List<String> errors;

    public BaseResponse(String type, List<String> errors) {
        this.type = type;
        this.errors = errors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}