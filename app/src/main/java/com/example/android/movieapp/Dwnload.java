package com.example.android.movieapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by elmothda on 21/10/2016.
 */


public class Dwnload extends AsyncTask<String, Integer, List<Movies>> {



    @Override
    protected List<Movies> doInBackground(String... params) {
        // task will done in background
        String link = params[0];
        String result = null;
        List<Movies> moviesList = new ArrayList<>();
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuffer buffer=new StringBuffer();
            String line;
            while ((line=reader.readLine())!=null){
                buffer.append(line+"\n");

        }
            if(buffer.length()==0){
                return null;
            }
            result=buffer.toString();
//            builder.toString();
            JSONObject object = new JSONObject(result);

            JSONArray array = object.getJSONArray("results");
            for (int i=0;i<array.length();i++){
                JSONObject object1 = array.getJSONObject(i);
                Movies movies = new Movies(object1);
                moviesList.add(movies);
            }
        }

            catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return moviesList;


    }

  /*  @Override
    protected void onPostExecute(List<Movies>  moviesList) {
        super.onPostExecute( moviesList);


    }*/

}
