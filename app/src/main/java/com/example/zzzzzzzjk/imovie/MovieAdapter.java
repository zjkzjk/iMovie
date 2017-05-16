package com.example.zzzzzzzjk.imovie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 17922 on 2017/4/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    List<Movie> movieList = new ArrayList<>();
    Context mContext;
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        final View view;

        view = LayoutInflater.from(mContext).inflate(R.layout.movie_item, parent, false);

        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.movieView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Movie movie = movieList.get(position);
                Intent intent = new Intent(view.getContext(),DetailActivity.class);
                intent.putExtra("name",movie.getName());
                intent.putExtra("imgId",movie.getImgId());
                view.getContext().startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        Picasso.with(mContext)
                .load(movie.getImgId())
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
//                        Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(mContext, "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
//        Glide.with(mContext).load(movie.getImgId()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        holder.textView.setText(movie.getName());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
    public MovieAdapter(List<Movie> movies){
        movieList = movies;

    }

    public void addAll(List<Movie> movies) {
        movieList = movies;
    }

    public void clear() {
        movieList.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View movieView;
        ImageView imageView;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            movieView = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.classify_cycler_img);
            textView = (TextView) itemView.findViewById(R.id.classify_ctxt);
        }
    }
}
