package com.example.graduationproject.core;

import java.util.List;

public class BaseResponse<D, I> {
    private String status;
    private D data;
    private I included;
    private List<Error> errors;

    public static class Error {
        private String title;
        private String message;
    }

    public BaseResponse(String status, D data, I included, List<Error> errors) {
        this.status = status;
        this.data = data;
        this.included = included;
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public I getIncluded() {
        return included;
    }

    public void setIncluded(I included) {
        this.included = included;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}