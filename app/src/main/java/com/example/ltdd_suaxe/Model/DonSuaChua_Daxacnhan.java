package com.example.ltdd_suaxe.Model;

import java.util.Date;
import java.util.List;

public class DonSuaChua_Daxacnhan{
    private String _id;
    private String TenCuaHang;
    private String IdKhachHang;
    private  String IdCuaHang;
    private Date NgayDatDon;
    private List <String> DichVu;
    private String DiaChi;
    private String GhiChu;
    private String TrangThai;
    private String HinhAnh;
    private String SDT;



    public DonSuaChua_Daxacnhan(String sdt, String hinhAnh, String trangThai, String ghiChu, String diaChi, List <String> dichVu, Date ngayDatDon, String idCuaHang, String idKhachHang, String tenCuaHang, String _id) {

        SDT = sdt;
        HinhAnh = hinhAnh;
        TrangThai = trangThai;
        GhiChu = ghiChu;
        DiaChi = diaChi;
        DichVu = dichVu;
        NgayDatDon = ngayDatDon;
        IdCuaHang = idCuaHang;
        IdKhachHang = idKhachHang;
        TenCuaHang = tenCuaHang;
        this._id = _id;

    }
    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTenCuaHang() {
        return TenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        TenCuaHang = tenCuaHang;
    }

    public String getIdKhachHang() {
        return IdKhachHang;
    }

    public void setIdKhachHang(String idKhachHang) {
        IdKhachHang = idKhachHang;
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

    public List <String> getDichVu() {
        return DichVu;
    }

    public void setDichVu(List <String> dichVu) {
        DichVu = dichVu;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
