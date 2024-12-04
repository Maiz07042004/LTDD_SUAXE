package com.example.ltdd_suaxe.Model;

import java.util.List;

public class User {
    private String _id;
    private String SDT;
    private List<String> CuaHangDaLuu;
    private String Email;
    private String GioiTinh;
    private String Password;
    private String TenKhachHang;

    // Getter và Setter
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public List<String> getCuaHangDaLuu() {
        return CuaHangDaLuu;
    }

    public void setCuaHangDaLuu(List<String> cuaHangDaLuu) {
        CuaHangDaLuu = cuaHangDaLuu;
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

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }
}

