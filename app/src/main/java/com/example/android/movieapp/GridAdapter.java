package com.example.android.movieapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by elmothda on 21/10/2016.
 */

public class GridAdapter extends BaseAdapter {
    List<Movies> list;
    Context context ;

    public GridAdapter(Context c,List<Movies>l ){
        list = l;
        context = c;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView  imageView;

        if(convertView == null){
            imageView = new ImageView(context);

        }else{
            imageView = (ImageView) convertView;
        }

        Picasso.with(context)
                //Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);

                .load("http://image.tmdb.org/t/p/w185/"+list.get(position).moviePath)
                .into(imageView);
        imageView.getAdjustViewBounds();

        return imageView;

    }

    public void addAll(List<Movies>l){
        list.clear();
        list.addAll(l);
        notifyDataSetChanged();
    }
}
