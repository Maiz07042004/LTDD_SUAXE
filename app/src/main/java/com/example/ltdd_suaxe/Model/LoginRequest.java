package com.example.ltdd_suaxe.Model;

public class LoginRequest {
    private String SDT;
    private String Password;

    // Constructor
    public LoginRequest(String SDT, String Password) {
        this.SDT = SDT;
        this.Password = Password;
    }

    // Getter and Setter methods
    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}

