package com.example.android.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements Iinterface {

    boolean twoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {

            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            GVFragment gvFragment = new GVFragment();
            gvFragment.setIinterface(this);

            fragmentTransaction.add(R.id.fragcontainer, gvFragment);
            fragmentTransaction.commit();
            if (null != findViewById(R.id.fldetails)) {
                twoPane = true;
            }


        }}
        @Override
        public void setSelected (Movies name){

            if (!twoPane) {
                Intent i = new Intent(this, MovieDetails.class);
                i.putExtra("name", name);
                startActivity(i);
            } else {
                MovieDetailsFragment MDFragment = new MovieDetailsFragment();
                Bundle extras = new Bundle();
                extras.putSerializable("name", name);
                MDFragment.setArguments(extras);
                getSupportFragmentManager().beginTransaction().replace(R.id.fldetails, MDFragment, "").commit();
            }
        }
    }
