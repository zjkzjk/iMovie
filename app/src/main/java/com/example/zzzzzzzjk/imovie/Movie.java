package com.example.zzzzzzzjk.imovie;

/**
 * Created by 17922 on 2017/4/17.
 */

public class Movie {
    int imgId;
    String name;
    public Movie(String Name,int ImgId){
        imgId = ImgId;
        name = Name;
    }

    public String getName() {
        return name;
    }

    public int getImgId() {
        return imgId;
    }
}
