package com.example.zzzzzzzjk.imovie;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import butterknife.ButterKnife;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.zzzzzzzjk.imovie.sync.ImovieAsyncTaskLoader;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>> {

    @BindView(R.id.view_refresh) SwipeRefreshLayout refresh;
    @BindView(R.id.recycler) RecyclerView recyclerView;
    @BindView(R.id.empty_view) TextView mEmptyStateTextView;

    private MovieAdapter adapter;
    private boolean isConnected;

    String Uri = "http://api.themoviedb.org/3/movie/";
    String select1 = "popular";
    String select2 = "top_rated";
    String language = "?language=zh&api_key=";
    LoaderManager loaderManager;
    String[] path = {Uri+select1+language+BuildConfig.API_KEY,Uri+select2+language+BuildConfig.API_KEY};

    @Override
    protected void onRestart() {
        super.onRestart();
        loaderManager.restartLoader(0, null, this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用butter knife绑定控件，省去冗余代码
        ButterKnife.bind(this);

        //recyclerview adapter定义

        GridLayoutManager glm = new GridLayoutManager(MainActivity.this, 2);;
        recyclerView.setLayoutManager(glm);

        adapter = new MovieAdapter(new ArrayList<Movie>());
        recyclerView.setAdapter(adapter);

        //网络判定，当没有网络时显示为空页面
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

        //下拉刷新
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
    public void updateMovie(){

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
        return super.onOptionsItemSelected(item);

    }
    // AsyncTaskLoader 接口方法
    // 创建接口（后台线程）
    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String select_path = prefs.getString(getString(R.string.pref_units_key),getString(R.string.pref_units_popular));
        if(select_path.equals("popular")){
            return new ImovieAsyncTaskLoader(MainActivity.this,path[0]);
        }else {
            return new ImovieAsyncTaskLoader(MainActivity.this,path[1]);
        }


    }
    //当后台线程加载完毕执行，执行在主线程
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
        refresh.setRefreshing(false);
    }
    //Loader重置
    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        adapter.clear();
    }

}
