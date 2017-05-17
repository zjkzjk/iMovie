package com.example.zzzzzzzjk.imovie;


import android.content.Intent;
import android.app.LoaderManager;
import android.content.Loader;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.detail_img) ImageView imageView;
    @BindView(R.id.detail_title) TextView tv_title;
    @BindView(R.id.detail_vote) TextView tv_vote;
    @BindView(R.id.detail_airticl) TextView tv_airticl;
    @BindView(R.id.detail_year) TextView tv_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //使用butter knife绑定控件，省去冗余代码
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Movie movie1 = intent.getParcelableExtra("movie");

        Log.d("Detail:", movie1.getTv_title());
        Picasso.with(DetailActivity.this)
                .load(movie1.getImgId())
                .resize(500,0)
                .placeholder(R.drawable.error)
                .error(R.drawable.error)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
//                        Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError() {
                        Toast.makeText(DetailActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
        tv_title.setText(movie1.getTv_title());
        tv_vote.setText(movie1.getTv_vote());
        tv_year.setText(movie1.getTv_year());
        tv_airticl.setText(movie1.getTv_airticl());

    }
}
