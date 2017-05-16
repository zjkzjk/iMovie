package com.example.zzzzzzzjk.imovie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView imageView = ((ImageView) findViewById(R.id.detail_image));
        TextView textView = (TextView) findViewById(R.id.detail_text);
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String imgId = intent.getStringExtra("imgId");
        Picasso.with(DetailActivity.this)
                .load(imgId)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(DetailActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(DetailActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
        textView.setText(name);

    }
}
