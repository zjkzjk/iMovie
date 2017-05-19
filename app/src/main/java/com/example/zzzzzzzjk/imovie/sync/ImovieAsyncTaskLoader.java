package com.example.zzzzzzzjk.imovie.sync;


import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.zzzzzzzjk.imovie.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjk on 2017/5/16.
 */

public class ImovieAsyncTaskLoader extends AsyncTaskLoader<List<Movie>> {
    String mUrl;
    boolean iscon;

    public ImovieAsyncTaskLoader(Context context, String url, boolean isConnected) {
        super(context);
        mUrl = url;
        iscon = isConnected;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {
        List<Movie> movies = new ArrayList<Movie>();
        if (iscon == true) {
            HttpQutils qutils = new HttpQutils();
            movies = qutils.fetchMovieData(mUrl);
        }
        return movies;
    }
}
