package com.example.zzzzzzzjk.imovie.sync;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.zzzzzzzjk.imovie.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjk on 2017/5/16.
 */

public class ImovieAsyncTaskLoader extends AsyncTaskLoader<List<Movie>> {

    String mUrl;
    public ImovieAsyncTaskLoader(Context context,String url) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {
        if (mUrl == null){
            return null;
        }
        List<Movie> movies = new ArrayList<Movie>();
        HttpQutils qutils = new HttpQutils();
        movies.addAll(qutils.fetchMovieData(mUrl));
        return movies;
    }
}
