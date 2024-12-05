package com.example.ltdd_suaxe.Model;

import java.util.Date;
import java.util.List;

public class ChiTietDatHangResponse {
    private String _id;
    private String TrangThai;
    private Date NgayDatDon;
    private List<String> DichVu;
    private String DiaChi;
    private String TenKhachHang;
    private String SDT;
    private String TenCuaHang;
    private String IdCuaHang;
    private String HinhAnh;
    private Boolean DaLike;

    public ChiTietDatHangResponse(Boolean daLike,String _id, String diaChi, List<String> dichVu, String hinhAnh, String idCuaHang, Date ngayDatDon, String SDT, String tenCuaHang, String tenKhachHang, String trangThai) {
        this._id = _id;
        DiaChi = diaChi;
        DichVu = dichVu;
        HinhAnh = hinhAnh;
        IdCuaHang = idCuaHang;
        NgayDatDon = ngayDatDon;
        this.SDT = SDT;
        TenCuaHang = tenCuaHang;
        TenKhachHang = tenKhachHang;
        TrangThai = trangThai;
        DaLike=daLike;
    }

    public Boolean getDaLike() {
        return DaLike;
    }

    public void setDaLike(Boolean daLike) {
        DaLike = daLike;
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

    public String getTenCuaHang() {
        return TenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        TenCuaHang = tenCuaHang;
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
