package com.example.ltdd_suaxe.Model;

import java.util.Date;
import java.util.List;

public class DonSuaChua {
    private String _id;
    private String IdKhachHang;
    private  String IdCuaHang;
    private Date NgayDatDon;
    private List<String> DichVu;
    private String DiaChi;
    private String GhiChu;
    private String TrangThai;

    public DonSuaChua(String _id, String diaChi, List<String> dichVu, String ghiChu, String idCuaHang, String idKhachHang, Date ngayDatDon, String trangThai) {
        this._id = _id;
        DiaChi = diaChi;
        DichVu = dichVu;
        GhiChu = ghiChu;
        IdCuaHang = idCuaHang;
        IdKhachHang = idKhachHang;
        NgayDatDon = ngayDatDon;
        TrangThai = trangThai;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public Date getNgayDatDon() {
        return NgayDatDon;
    }

    public void setNgayDatDon(Date ngayDatDon) {
        NgayDatDon = ngayDatDon;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
