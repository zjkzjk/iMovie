package com.example.zzzzzzzjk.imovie;

/**
 * Created by 17922 on 2017/4/17.
 */

public class Movie {
    private int id;
    private String imgId;
    private String name;
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
