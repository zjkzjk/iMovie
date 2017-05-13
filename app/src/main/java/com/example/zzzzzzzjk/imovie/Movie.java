package com.example.zzzzzzzjk.imovie;

/**
 * Created by 17922 on 2017/4/17.
 */

public class Movie {
    String imgId;
    String name;
    public Movie(String Name,String ImgId){
        imgId = ImgId;
        name = Name;
    }

    public String getName() {
        return name;
    }

    public String getImgId() {
        return imgId;
    }
}
