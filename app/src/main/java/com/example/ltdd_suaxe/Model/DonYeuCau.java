package com.example.ltdd_suaxe.Model;

import java.util.Date;
import java.util.List;

public class DonYeuCau {
    private String _id;
    private String IdKhachHang;
    private  String IdCuaHang;
    private Date NgayDatDon;
    private List<String> DichVu;
    private String DiaChi;
    private String GhiChu;
    private String TrangThai;
    private String HinhAnh;
    private String TenKhachHang;
    private String SDT;

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

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
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

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public DonYeuCau(String _id, String diaChi, List<String> dichVu, String ghiChu, String hinhAnh, String idCuaHang, String idKhachHang, Date ngayDatDon, String SDT, String tenKhachHang, String trangThai) {
        this._id = _id;
        DiaChi = diaChi;
        DichVu = dichVu;
        GhiChu = ghiChu;
        HinhAnh = hinhAnh;
        IdCuaHang = idCuaHang;
        IdKhachHang = idKhachHang;
        NgayDatDon = ngayDatDon;
        this.SDT = SDT;
        TenKhachHang = tenKhachHang;
        TrangThai = trangThai;
    }
}
