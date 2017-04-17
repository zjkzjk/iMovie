package com.example.zzzzzzzjk.imovie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.HeaderViewListAdapter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Movie> movieList = new ArrayList<Movie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMovie();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        GridLayoutManager glm = new GridLayoutManager(this, 2);;
        recyclerView.setLayoutManager(glm);
        MovieAdapter adapter = new MovieAdapter(movieList);
        recyclerView.setAdapter(adapter);

    }
    public void initMovie(){
        for (int i = 0 ;i <20;i++){
            Movie movie1 = new Movie("1111",R.drawable.sample_2);
            movieList.add(movie1);
            Movie movie2 = new Movie("2222",R.drawable.sample_3);
            movieList.add(movie2);
        }
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
        }
        return super.onOptionsItemSelected(item);

    }
}
