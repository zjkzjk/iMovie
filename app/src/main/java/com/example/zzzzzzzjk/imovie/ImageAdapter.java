package com.example.zzzzzzzjk.imovie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Zzzzzzzjk on 2017/2/9.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }
    @Override
    public int getCount() {
        return mThumbIds.length;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view= View.inflate(mContext,R.layout.image_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.movie_image);

        imageView.setImageResource(mThumbIds[position]);


        return view;
    }
    private Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_2, R.drawable.sample_3

    };

}
