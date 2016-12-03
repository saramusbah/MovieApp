package com.example.android.movieapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by elmothda on 20/10/2016.
 */

public class Movies implements Serializable {

    public String movieName,movieVote,moviePath,movieDate,movieOverview;
    public int id;
    public Movies(){}

    public Movies(JSONObject object1) throws JSONException {
        movieName = object1.getString("title");
        id = object1.getInt("id");
        movieDate = object1.getString("release_date");
        moviePath = object1.getString("poster_path");
        movieVote = object1.getString("vote_average");
        movieOverview=object1.getString("overview");
    }


}
