package com.example.ltdd_suaxe.Model;

import java.util.List;

public class DangKyKhachHangRequest {
    private String SDT;
    private String Email;
    private String GioiTinh;
    private String Password;
    private String TenKhachHang;

    public DangKyKhachHangRequest(String email, String gioiTinh, String password, String SDT, String tenKhachHang) {
        Email = email;
        GioiTinh = gioiTinh;
        Password = password;
        this.SDT = SDT;
        TenKhachHang = tenKhachHang;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }
}
