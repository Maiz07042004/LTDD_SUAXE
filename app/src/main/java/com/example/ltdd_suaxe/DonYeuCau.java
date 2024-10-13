package com.example.ltdd_suaxe;

import java.util.Date;

public class DonYeuCau {
    private String TenKhachHang;
    private String SoDienThoai;
    private String[] DichVu;
    private String DiaChi;
    private String TrangThai;
    private int hinh;

    public DonYeuCau(String tenKhachHang,String soDienThoai,String[] dichVu,String diaChi, String trangThai,int hinh) {
        DiaChi = diaChi;
        DichVu = dichVu;
        this.hinh = hinh;
        SoDienThoai = soDienThoai;
        TenKhachHang = tenKhachHang;
        TrangThai = trangThai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String[] getDichVu() {
        return DichVu;
    }

    public void setDichVu(String[] dichVu) {
        DichVu = dichVu;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
