package com.example.ltdd_suaxe.Model;

public class CapNhatCuaHangRequest {
    private String TenCuaHang;
    private String SDT;
    private  String MoTa;
    private  String DiaChi;
    private String Email;
    private String TenQuan;

    public CapNhatCuaHangRequest(String diaChi, String email, String moTa, String SDT, String tenCuaHang, String tenQuan) {
        DiaChi = diaChi;
        Email = email;
        MoTa = moTa;
        this.SDT = SDT;
        TenCuaHang = tenCuaHang;
        TenQuan = tenQuan;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getTenCuaHang() {
        return TenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        TenCuaHang = tenCuaHang;
    }

    public String getTenQuan() {
        return TenQuan;
    }

    public void setTenQuan(String tenQuan) {
        TenQuan = tenQuan;
    }
}
