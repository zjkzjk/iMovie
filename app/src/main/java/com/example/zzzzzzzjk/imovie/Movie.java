package com.example.zzzzzzzjk.imovie;

/**
 * Created by 17922 on 2017/4/17.
 */

public class Movie {
    int id;
    String imgId;
    String name;
    public Movie(String Name,String ImgId,int Id){
        imgId = ImgId;
        id = Id;
        name = Name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgId() {
        return imgId;
    }
}
