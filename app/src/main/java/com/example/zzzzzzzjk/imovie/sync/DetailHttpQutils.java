package com.example.zzzzzzzjk.imovie.sync;

import android.util.Log;

import com.example.zzzzzzzjk.imovie.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zjk on 2017/5/16.
 */

public class DetailHttpQutils {
    int ID;
    public DetailHttpQutils(int intentID){
        ID = intentID;
    }
    List<String> list = new ArrayList<String>();
    //OKHttp 连接网络
    public List<String> fetchMovieData(String url){
        Log.d("url:", url);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url
                (url).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            String responseData = response.body().string();
            parseJson(responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    private void parseJson(String JSONData){

        try{
            JSONObject jsonObject = new JSONObject(JSONData);
            JSONArray results = jsonObject.getJSONArray("results");
            for (int i = 0;i <results.length();i++ ){
                JSONObject jsonResult = results.getJSONObject(i);
                int id = jsonResult.getInt("id");
                if (ID == id) {
                    String title = jsonResult.getString("original_title");
                    list.add(title);
                    String img = "https://image.tmdb.org/t/p/w500"+jsonResult.getString("poster_path");
                    list.add(img);
                    String airticl = jsonResult.getString("overview");
                    list.add(airticl);
                    String date = jsonResult.getString("release_date");
                    list.add(date);
                    String vote = jsonResult.getString("vote_average");
                    list.add(vote);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
