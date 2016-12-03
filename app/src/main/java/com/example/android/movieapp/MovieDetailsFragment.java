package com.example.android.movieapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.movieapp.data.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsFragment extends Fragment {

    Helper h ;

    //Context context;
    Movies movie;
    ArrayList<Reviews> reviewlist;
    Reviews reviews;
    Trailer trailer;
    ArrayList<Trailer> trailerList;
    Intent intent;
    Uri uri;
    List<Trailer> tmp;

    public MovieDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        if (savedInstanceState == null) {


            //Intent intent = getIntent();
            Bundle bundle = getArguments();


            //movie = (Movies) intent.getSerializableExtra("Key");
            movie = (Movies) bundle.get("name");

            final ImageView imageView = (ImageView) view.findViewById(R.id.mposter);
            imageView.getAdjustViewBounds();

            TextView title = (TextView) view.findViewById(R.id.mtitle);
            TextView date = (TextView) view.findViewById(R.id.mdate);
            TextView rate = (TextView) view.findViewById(R.id.mrate);
            TextView overview = (TextView) view.findViewById(R.id.moverview);


            title.setText(movie.movieName);
            date.setText(movie.movieDate);
            rate.setText(movie.movieVote);
            overview.setText(movie.movieOverview);

            Picasso.with(getContext())
                    .load("http://image.tmdb.org/t/p/w325/" + movie.moviePath)
                    .into(imageView);


            reviewlist =new ArrayList<Reviews>();
            final ListView review ;
            review=(ListView)view.findViewById(R.id.reviewlist1);
            Rdownload task2 = new Rdownload(){
                @Override
                protected void onPostExecute(List<Reviews> reviewlist) {

                    Radaptor reviews = new Radaptor(getContext(),reviewlist);
                    review.setAdapter(reviews);
                }
            };
            task2.execute("https://api.themoviedb.org/3/movie/"+movie.id+"/reviews?api_key=4e519562a614276ee3db5686df6a36e0");


            trailerList =new ArrayList<Trailer>();
            final ListView t;
            t=(ListView)view.findViewById(R.id.ltrailer);
            TrailDownload task3 =new TrailDownload() {
                @Override
                protected void onPostExecute (List<Trailer> tlist){
                 // Log.i("check list R",tlist.get(0).key);
                    tmp =new ArrayList<Trailer>();
                    tmp = tlist;
                    TraiAdaptor adaptor = new TraiAdaptor(getContext(),tlist);
                    t.setAdapter(adaptor);
                }

            };

            task3.execute("https://api.themoviedb.org/3/movie/"+movie.id+"/videos?api_key=4e519562a614276ee3db5686df6a36e0");


            t.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                    uri = Uri.parse("https://www.youtube.com/watch?v="+tmp.get(position));
//                    intent = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(intent);
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+tmp.get(position).key)));



                }
            });


        }

        h = new Helper(getContext());
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = h.insert(movie);
                if (isInserted == true) {
                    Snackbar.make(view, "Added to favourites", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "Failed", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        return view;
    }


}

