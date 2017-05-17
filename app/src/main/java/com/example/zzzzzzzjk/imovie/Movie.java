package com.example.zzzzzzzjk.imovie;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 17922 on 2017/4/17.
 */

public class Movie implements Parcelable {
    private int id;
    private String imgId,name,tv_title,tv_vote,tv_year,tv_airticl;

    public Movie(Parcel in) {
        id = in.readInt();
        imgId = in.readString();
        name = in.readString();
        tv_title = in.readString();
        tv_vote = in.readString();
        tv_year = in.readString();
        tv_airticl = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTv_title() {
        return tv_title;
    }

    public String getTv_vote() {
        return tv_vote;
    }

    public String getTv_year() {
        return tv_year;
    }

    public String getTv_airticl() {
        return tv_airticl;
    }

    public Movie(int id1, String img1, String name1, String title1,
                 String vote1, String date1, String airticl1) {
        id = id1;
        imgId = img1;
        name = name1;
        tv_title = title1;
        tv_vote = vote1;
        tv_year = date1;
        tv_airticl = airticl1;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(imgId);
        dest.writeString(name);
        dest.writeString(tv_title);
        dest.writeString(tv_vote);
        dest.writeString(tv_year);
        dest.writeString(tv_airticl);
    }
}
