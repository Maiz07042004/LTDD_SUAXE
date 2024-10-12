package com.example.ltdd_suaxe;

public class nCuaHangDaLuu {

    private String Ten;
    private int Like;
    private int Hinh;
    private int Luu;



    public nCuaHangDaLuu(String ten, int like, int hinh, int luu) {
        Ten = ten;
        Like = like;
        Hinh = hinh;
        Luu = luu;
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

    public int getLuu() {return Luu;}

    public void setLuu(int luu) {Luu = luu;}
}


