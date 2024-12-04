package com.example.ltdd_suaxe.Model;

public class LoginCuaHangResponse {
    private int code;
    private String message;
    private String cuaHangId;

    public LoginCuaHangResponse(int code, String cuaHangId, String message) {
        this.code = code;
        this.cuaHangId = cuaHangId;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCuaHangId() {
        return cuaHangId;
    }

    public void setCuaHangId(String cuaHangId) {
        this.cuaHangId = cuaHangId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
