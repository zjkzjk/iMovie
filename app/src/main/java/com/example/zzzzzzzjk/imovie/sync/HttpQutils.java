package com.example.zzzzzzzjk.imovie.sync;

import android.os.Parcel;
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

public class HttpQutils {
    public HttpQutils(){

    }
    List<Movie> movies = new ArrayList<Movie>();
    //OKHttp 连接网络
    public List<Movie> fetchMovieData(String url){
//        Log.d("url:", url);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url
                (url).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            String responseData = response.body().string();
//                Log.d("MainActivity","responseData="+responseData);
            parseJson(responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }
    private void parseJson(String JSONData){

        try{
            JSONObject jsonObject = new JSONObject(JSONData);
            JSONArray results = jsonObject.getJSONArray("results");
            for (int i = 0;i <results.length();i++ ){
                JSONObject jsonResult = results.getJSONObject(i);
                int id = jsonResult.getInt("id");
//                    Log.d("MainActivity","id="+id);
                String name = jsonResult.getString("title");
//                    Log.d("MainActivity","name="+name);
                String img = "https://image.tmdb.org/t/p/w185"+jsonResult.getString("backdrop_path");
//                    Log.d("MainActivity","img="+img);
                String title = jsonResult.getString("original_title");
                //  Log.d("MainActivity","original_title="+title);
                String airticl = jsonResult.getString("overview");

                String date = jsonResult.getString("release_date");
                String vote = jsonResult.getString("vote_average");
                Movie movie = new Movie(id,img,name,title,vote,date,airticl);
                movies.add(movie);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
