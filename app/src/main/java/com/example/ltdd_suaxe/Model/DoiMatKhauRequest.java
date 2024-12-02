package com.example.ltdd_suaxe.Model;

public class DoiMatKhauRequest {
    private String Password;
    private String NewPassword;

    public DoiMatKhauRequest(String password,String newPassword ) {
        NewPassword = newPassword;
        Password = password;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
