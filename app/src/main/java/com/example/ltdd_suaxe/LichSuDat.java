package com.example.ltdd_suaxe;

import java.lang.reflect.Array;
import java.util.Date;

public class LichSuDat {
    private String TenCuaHang;
    private String[] DichVu;
    private Date NgayDat;
    private String TrangThai;
    private int hinh;

    public LichSuDat(String tenCuaHang, String[] dichVu, Date ngayDat, String trangThai, int hinh) {
        TenCuaHang = tenCuaHang;
        DichVu = dichVu;
        NgayDat = ngayDat;
        TrangThai = trangThai;
        this.hinh = hinh;
    }

    public String getTenCuaHang() {
        return TenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        TenCuaHang = tenCuaHang;
    }

    public String[] getDichVu() {
        return DichVu;
    }

    public void setDichVu(String[] dichVu) {
        DichVu = dichVu;
    }

    public Date getNgayDat() {
        return NgayDat;
    }

    public void setNgayDat(Date ngayDat) {
        NgayDat = ngayDat;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}
