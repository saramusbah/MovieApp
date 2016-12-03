package com.example.android.movieapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.movieapp.data.Contract;
import com.example.android.movieapp.data.Helper;

import java.util.ArrayList;
import java.util.List;


public class GVFragment extends Fragment {
     GridView grid ;
     GridAdapter adapter ;
     List<Movies>list ;
     Intent intent;
    ArrayList<String> l;


    private Iinterface inter;

    void setIinterface(Iinterface interf ) {
        this.inter = interf;
    }

    public GVFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
      inflater.inflate(R.menu.menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if( id == R.id.toptrated)
        {
            Dwnload most = new Dwnload(){

                @Override
                protected void onPostExecute(List<Movies> movies) {
                    super.onPostExecute(movies);
                    adapter.addAll( movies);
                    grid.setAdapter(adapter);
                }
            };
            most.execute("https://api.themoviedb.org/3/movie/top_rated?api_key=4e519562a614276ee3db5686df6a36e0");
            //return true;
        }
           else if( id == R.id.mostpopular)
            {
                Dwnload top = new Dwnload(){

                    @Override
                    protected void onPostExecute(List<Movies> movies) {
                        super.onPostExecute(movies);
                        adapter.addAll( movies);
                        grid.setAdapter(adapter);
                    }
                };
                top.execute("https://api.themoviedb.org/3/movie/popular?api_key=4e519562a614276ee3db5686df6a36e0");
            }
        else if( id == R.id.favourits)
        {
            Helper helper=new Helper(getContext());
            Cursor cursor = helper.getAllData();
            ArrayList<Movies> List1 = new ArrayList<>();

            while(cursor.moveToNext()) {
                Movies movie = new Movies();
                movie.movieName = cursor.getString(cursor.getColumnIndex(Contract.Entry.MOVIE_NAME)) ;
                movie.moviePath = cursor.getString(cursor.getColumnIndex(Contract.Entry.MOVIE_PATH)) ;
                movie.id = cursor.getInt(cursor.getColumnIndex(Contract.Entry.MOVIE_ID)) ;
                movie.movieDate = cursor.getString(cursor.getColumnIndex(Contract.Entry.RELEASE_DATE)) ;
                movie.movieOverview = cursor.getString(cursor.getColumnIndex(Contract.Entry.MOVIE_OVER_VIEW)) ;
                movie.movieVote = cursor.getString(cursor.getColumnIndex(Contract.Entry.MOVIE_VOTE)) ;
                List1.add(movie);
            }

            adapter.addAll(List1);
            grid.setAdapter(adapter);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_gv, container, false);

        grid = (GridView) view.findViewById(R.id.grid);
        list =new ArrayList<Movies>();
        Dwnload task = new Dwnload(){
            @Override
            protected void onPostExecute(List<Movies> moviesList) {
                super.onPostExecute( moviesList);
                //azabat el adapter b el list eli gayaly
              //  adapter.addAll( moviesList);
                list =new ArrayList<>();
                list=moviesList;
                adapter = new GridAdapter(view.getContext(), moviesList);
                grid.setAdapter(adapter);
            }
        };

        task.execute("https://api.themoviedb.org/3/movie/top_rated?api_key=4e519562a614276ee3db5686df6a36e0");


        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                // intent = new Intent(getContext(),MovieDetails.class);

                //intent.putExtra("Key",(Serializable) adapter.getItem(position));

                //startActivity(intent);

                inter.setSelected(list.get(position));


            }
        });


        return view;
    }




}
