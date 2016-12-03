package com.example.android.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MovieDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {

            setContentView(R.layout.activity_movie_details);
            Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
            setSupportActionBar(toolbar2);
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            MovieDetailsFragment detfragment = new MovieDetailsFragment();
            detfragment.setArguments(bundle);

            fragmentTransaction.add(R.id.fldetails, detfragment);
            fragmentTransaction.commit();
        }
    }
}