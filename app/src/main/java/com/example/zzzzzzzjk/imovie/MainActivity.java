package com.example.zzzzzzzjk.imovie;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.zzzzzzzjk.imovie.sync.ImovieAsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>> {
    MovieAdapter adapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout refresh;
    boolean isConnected;
    TextView mEmptyStateTextView;
    String Uri = "http://api.themoviedb.org/3/movie/";
    String select1 = "popular";
    String select2 = "top_rated";
    String language = "?language=zh&api_key=";
    LoaderManager loaderManager;
    String[] path = {Uri+select1+language+BuildConfig.API_KEY,Uri+select2+language+BuildConfig.API_KEY};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        refresh = (SwipeRefreshLayout) findViewById(R.id.view_refresh);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        GridLayoutManager glm = new GridLayoutManager(MainActivity.this, 2);;
        recyclerView.setLayoutManager(glm);

        adapter = new MovieAdapter(new ArrayList<Movie>());
        recyclerView.setAdapter(adapter);


        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        isConnected = networkInfo != null &&
                networkInfo.isConnectedOrConnecting();

        // If there is a network connection, fetch data
        if (isConnected == true) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(0, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            mEmptyStateTextView.setText("无网络连接");
            // Update empty state with no connection error message
        }

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isConnected == false){
//                    recyclerView.setVisibility(View.GONE);
                    mEmptyStateTextView.setText("无网络连接");
                    refresh.setRefreshing(false);
                }
                else {
//                    mEmptyStateTextView.setVisibility(View.GONE);
                    loaderManager.restartLoader(0,null,MainActivity.this);
                }

            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int getId = item.getItemId();

        if (getId == R.id.setting){
            startActivity(new Intent(this,SettingActivity.class));
            return true;
        }else if (getId == R.id.refresh){

        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        Log.d("MainActivity", "1");
        return new ImovieAsyncTaskLoader(MainActivity.this,path[0]);

    }
    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        adapter.clear();
        if (isConnected == false){
            recyclerView.setVisibility(View.GONE);
            mEmptyStateTextView.setText("无网络连接");
        }
        else {
            mEmptyStateTextView.setVisibility(View.GONE);
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
        if (data == null)
        {
            recyclerView.setVisibility(View.GONE);
        }
        else {
            mEmptyStateTextView.setVisibility(View.GONE);
        }
        refresh.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        adapter.clear();
    }

}
