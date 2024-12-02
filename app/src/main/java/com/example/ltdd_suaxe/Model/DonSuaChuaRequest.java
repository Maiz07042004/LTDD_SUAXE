package com.example.ltdd_suaxe.Model;

import java.util.Date;
import java.util.List;

public class DonSuaChuaRequest {
    private String IdKhachHang;
    private  String IdCuaHang;
    private List<String> DichVu;
    private String DiaChi;
    private String GhiChu;
    private String TrangThai;

    public DonSuaChuaRequest(String idKhachHang,String idCuaHang,List<String> dichVu,String diaChi,String ghiChu,String trangThai) {
        DiaChi = diaChi;
        DichVu = dichVu;
        GhiChu = ghiChu;
        IdCuaHang = idCuaHang;
        IdKhachHang = idKhachHang;
        TrangThai = trangThai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public List<String> getDichVu() {
        return DichVu;
    }

    public void setDichVu(List<String> dichVu) {
        DichVu = dichVu;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public String getIdCuaHang() {
        return IdCuaHang;
    }

    public void setIdCuaHang(String idCuaHang) {
        IdCuaHang = idCuaHang;
    }

    public String getIdKhachHang() {
        return IdKhachHang;
    }

    public void setIdKhachHang(String idKhachHang) {
        IdKhachHang = idKhachHang;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
