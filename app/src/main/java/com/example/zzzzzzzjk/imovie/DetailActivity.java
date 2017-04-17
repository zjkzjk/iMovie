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
        Bundle bundle=this.getIntent().getExtras();
        int imgId = bundle.getInt("imgId");
        imageView.setImageResource(imgId);
        textView.setText(name);

    }
}
