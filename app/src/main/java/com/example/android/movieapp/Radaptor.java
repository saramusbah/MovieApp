package com.example.android.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by elmothda on 01/12/2016.
 */

public class Radaptor extends BaseAdapter {
    List<Reviews> list;
    Context context;
   public Radaptor(Context context, List<Reviews> list){
       this.list=list;
       this.context=context;
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
        TextView text1,text2;
        //ImageView image;
        View grid;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            grid=inflater.inflate(R.layout.review,parent,false);
        }else{
            grid=convertView;
        }
        text1=(TextView)grid.findViewById(R.id.auther);
        text1.setText(list.get(position).auther);
        text2=(TextView)grid.findViewById(R.id.content);
        text2.setText(list.get(position).content);
       // image=(ImageView)grid.findViewById(R.id.img)
         //       image.setImageResource(R.drawable.user);
        return grid;
    }
    public void addAll(List<Reviews>l){
        list.addAll(l);
        notifyDataSetChanged();
    }
}
