package com.example.android.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class TraiAdaptor extends BaseAdapter{
    List<Trailer> list;
    Context context;
    public TraiAdaptor(Context c, List<Trailer>l ){
        list = l;
        context = c;
    }
    @Override
    public int getCount (){return list.size(); }


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
        TextView t;
        ImageView imageView;
        View v;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        if(convertView == null){
          //  imageView = new ImageView(context);
            v=inflater.inflate(R.layout.trailer,parent,false);

        }else{
           v= convertView;
        }
         imageView=(ImageView)v.findViewById(R.id.image);
        t=(TextView)v.findViewById(R.id.text);
        //        text1.setText(list.get(position).auther);

         t.setText(list.get(position).name);
        return v ;
    }

    public void addAll(List<Trailer>l){
        list.addAll(l);
        notifyDataSetChanged();
    }
}
