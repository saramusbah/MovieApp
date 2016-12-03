package com.example.android.movieapp;

import org.json.JSONException;
import org.json.JSONObject;



public class Trailer {
    public String key,name;
    public Trailer(){}
    public Trailer (JSONObject object1) throws JSONException {


        key=object1.getString("key");
        name=object1.getString("name");
    }
}
