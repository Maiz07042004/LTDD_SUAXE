package com.example.ltdd_suaxe.Model;

public class CapNhatCuaHangResponse {
    private String _id;
    private String TenCuaHang;
    private String SDT;
    private String HinhAnh;
    private  String MoTa;
    private  String DiaChi;
    private int Like;
    private String Email;
    private String Password;
    private String IdQuan;
    private String TenQuan;

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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getIdQuan() {
        return IdQuan;
    }

    public void setIdQuan(String idQuan) {
        IdQuan = idQuan;
    }

    public int getLike() {
        return Like;
    }

    public void setLike(int like) {
        Like = like;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
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

    public CapNhatCuaHangResponse(String _id, String diaChi, String email, String hinhAnh, String idQuan, int like, String moTa, String password, String SDT, String tenCuaHang, String tenQuan) {
        this._id = _id;
        DiaChi = diaChi;
        Email = email;
        HinhAnh = hinhAnh;
        IdQuan = idQuan;
        Like = like;
        MoTa = moTa;
        Password = password;
        this.SDT = SDT;
        TenCuaHang = tenCuaHang;
        TenQuan = tenQuan;
    }
}
