package com.example.zzzzzzzjk.imovie.sync;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by zjk on 2017/5/16.
 */

public class DetailAsyncTaskLoader extends AsyncTaskLoader<List<String>> {
    String mUrl;
    int id;
    public DetailAsyncTaskLoader(Context context,String url,int intentID) {
        super(context);
        id = intentID;
        mUrl = url;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<String> loadInBackground() {
        Log.d("DetailActivity", mUrl+"  "+id);
        DetailHttpQutils detailHttpQutils = new DetailHttpQutils(id);
        List<String> list = detailHttpQutils.fetchMovieData(mUrl);
        return list;
    }
}
