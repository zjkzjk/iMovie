package com.example.zzzzzzzjk.imovie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView imageView = ((ImageView) findViewById(R.id.detail_image));

        Intent intent = getIntent();

        if (intent != null) {
            byte [] bis=intent.getByteArrayExtra("bitmap");
            Bitmap bitmap=BitmapFactory.decodeByteArray(bis, 0, bis.length);

            imageView.setImageBitmap(bitmap);
        } else {
            Log.v("DetailActivity", "intent is empty");
        }


    }
}
