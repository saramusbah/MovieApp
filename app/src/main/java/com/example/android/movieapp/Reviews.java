package com.example.android.movieapp;


import org.json.JSONException;
import org.json.JSONObject;

public class Reviews {
   public String auther,content;

    public Reviews(){
     }

    public Reviews(JSONObject object1) throws JSONException {
        auther= object1.getString("author");
        content=object1.getString("content");

    }



}
