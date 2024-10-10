package com.example.ltdd_suaxe;

public class CuaHang {
    private String Ten;
    private int Like;
    private int Hinh;

    public CuaHang(String ten, int like, int hinh) {
        Ten = ten;
        Like = like;
        Hinh = hinh;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public int getLike() {
        return Like;
    }

    public void setLike(int like) {
        Like = like;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }
}
