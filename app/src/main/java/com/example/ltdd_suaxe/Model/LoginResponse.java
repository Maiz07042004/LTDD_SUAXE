package com.example.ltdd_suaxe.Model;

public class LoginResponse {
    private int code;
    private String message;
    private String userId;

    // Constructor
    public LoginResponse(int code, String message, String userId) {
        this.code = code;
        this.message = message;
        this.userId = userId;
    }

    // Getter and Setter methods
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

