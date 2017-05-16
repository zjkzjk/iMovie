package com.example.zzzzzzzjk.imovie;

import android.app.LoaderManager;
import android.content.Intent;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.zzzzzzzjk.imovie.sync.ImovieAsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>> {
    MovieAdapter adapter;
    SwipeRefreshLayout refresh;
    String pop_path="http://api.themoviedb.org/3/movie/popular?language=zh&api_key=3e312b7d4498d5ca3f449a3120adbee0";
    String top_path="http://api.themoviedb.org/3/movie/top_rated?language=zh&api_key=3e312b7d4498d5ca3f449a3120adbee0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refresh = (SwipeRefreshLayout) findViewById(R.id.view_refresh);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        GridLayoutManager glm = new GridLayoutManager(MainActivity.this, 2);;
        recyclerView.setLayoutManager(glm);
        adapter = new MovieAdapter(new ArrayList<Movie>());
        recyclerView.setAdapter(adapter);


        // 引用 LoaderManager，以便与 loader 进行交互。
        LoaderManager loaderManager = getLoaderManager();

        // 初始化 loader。传递上面定义的整数 ID 常量并为为捆绑
        // 传递 null。为 LoaderCallbacks 参数（由于
        // 此活动实现了 LoaderCallbacks 接口而有效）传递此活动。
        loaderManager.initLoader(0, null, this);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

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
        return new ImovieAsyncTaskLoader(MainActivity.this,pop_path);

    }
    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
        refresh.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        adapter.clear();
    }

}
