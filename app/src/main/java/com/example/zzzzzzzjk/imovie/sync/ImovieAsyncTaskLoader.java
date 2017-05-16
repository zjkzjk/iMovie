package com.example.zzzzzzzjk.imovie.sync;


import android.content.AsyncTaskLoader;
import android.content.Context;
import com.example.zzzzzzzjk.imovie.Movie;
import java.util.List;

/**
 * Created by zjk on 2017/5/16.
 */

public class ImovieAsyncTaskLoader extends AsyncTaskLoader<List<Movie>> {

    String mUrl;
    public ImovieAsyncTaskLoader(Context context,String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {

        HttpQutils qutils = new HttpQutils();
        List<Movie> movies = qutils.fetchMovieData(mUrl);
        return movies;
    }
}
