package com.example.ltdd_suaxe.Model;

public class CapNhatKhachHangRequest {
    private String TenKhachHang;
    private String Email;
    private String SDT;
    private String GioiTinh;

    public CapNhatKhachHangRequest(String tenKhachHang,String email, String SDT, String gioiTinh ) {
        Email = email;
        GioiTinh = gioiTinh;
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
