package com.example.zzzzzzzjk.imovie;


import android.content.Intent;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzzzzzzjk.imovie.sync.DetailAsyncTaskLoader;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<String>>{
    TextView tv_title,tv_airticl,tv_year,tv_vote;
    ImageView imageView;
    int intentID;
    String Uri = "http://api.themoviedb.org/3/movie/";
    String select1 = "popular";
    String select2 = "top_rated";
    String language = "?language=zh&api_key=";
    android.app.LoaderManager loaderManager;
    String[] path = {Uri+select1+language+BuildConfig.API_KEY,Uri+select2+language+BuildConfig.API_KEY};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = ((ImageView) findViewById(R.id.detail_img));
        tv_title = (TextView) findViewById(R.id.detail_title);
        tv_airticl = (TextView) findViewById(R.id.detail_airticl);
        tv_year = (TextView) findViewById(R.id.detail_year);
        tv_vote = (TextView) findViewById(R.id.detail_vote);
        Intent intent = getIntent();
        intentID = intent.getIntExtra("id",0);
        // Get a reference to the LoaderManager, in order to interact with loaders.
        loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(0, null, this);

    }

    @Override
    public android.content.Loader<List<String>> onCreateLoader(int id, Bundle args) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DetailActivity.this);
        String select_path = prefs.getString(getString(R.string.pref_units_key),getString(R.string.pref_units_popular));
        if(select_path.equals("popular")) {
            return new DetailAsyncTaskLoader(this, path[0], intentID);
        }else {
            return new DetailAsyncTaskLoader(this, path[1], intentID);
        }
    }

    @Override
    public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
        tv_title.setText(data.get(0));
        Picasso.with(DetailActivity.this)
                .load(data.get(1))
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(DetailActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
        tv_airticl.setText(data.get(2));
        tv_year.setText(data.get(3));
        tv_vote.setText(data.get(4));
    }


    @Override
    public void onLoaderReset(Loader<List<String>> loader) {

    }
}
