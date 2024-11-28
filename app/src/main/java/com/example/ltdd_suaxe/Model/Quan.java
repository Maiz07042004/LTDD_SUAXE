package com.example.ltdd_suaxe.Model;

public class Quan {
    private String _id;
    private String TenQuan;
    private  String MoTa;
    private String HinhAnh;

    public Quan(String _id, String tenQuan, String moTa, String hinhAnh) {
        this._id = _id;
        HinhAnh = hinhAnh;
        MoTa = moTa;
        TenQuan = tenQuan;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getTenQuan() {
        return TenQuan;
    }

    public void setTenQuan(String tenQuan) {
        TenQuan = tenQuan;
    }
}
