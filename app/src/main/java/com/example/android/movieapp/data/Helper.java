package com.example.android.movieapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.movieapp.Movies;

import static com.example.android.movieapp.data.Contract.Entry.MOVIE_ID;
import static com.example.android.movieapp.data.Contract.Entry.MOVIE_NAME;
import static com.example.android.movieapp.data.Contract.Entry.MOVIE_OVER_VIEW;
import static com.example.android.movieapp.data.Contract.Entry.MOVIE_PATH;
import static com.example.android.movieapp.data.Contract.Entry.MOVIE_VOTE;
import static com.example.android.movieapp.data.Contract.Entry.RELEASE_DATE;
import static com.example.android.movieapp.data.Contract.Entry.TABLE_NAME;

public class Helper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "favourites.db";

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_LOCATION_TABLE = " CREATE TABLE " +
                TABLE_NAME + " ( " +
                MOVIE_NAME+" TEXT NOT NULL," +
                MOVIE_ID + " INTEGER PRIMARY KEY," +
                MOVIE_PATH + " TEXT UNIQUE NOT NULL, " +
                MOVIE_OVER_VIEW + " TEXT NOT NULL, " +
                RELEASE_DATE + " TEXT NOT NULL, " +
                MOVIE_VOTE + " REAL NOT NULL " +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_LOCATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
public boolean insert(Movies movie){
    //String movieName,int id,String movieVote,String moviePath,String movieOverview,String releaseDatem
    //    String movieName,movieVote,moviePath,movieDate,movieOverview;

    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues contentvalues = new ContentValues();
    contentvalues.put(Contract.Entry.MOVIE_NAME,movie.movieName);
    contentvalues.put(Contract.Entry.MOVIE_ID,movie.id);

    contentvalues.put(Contract.Entry.MOVIE_VOTE,movie.movieVote);
    contentvalues.put(Contract.Entry.MOVIE_PATH,movie.moviePath);
    contentvalues.put(Contract.Entry.MOVIE_OVER_VIEW,movie.movieOverview);
    contentvalues.put(Contract.Entry.RELEASE_DATE,movie.movieDate);
    long result = db.insert(Contract.Entry.TABLE_NAME,null,contentvalues);
    if(result==-1)
        return false;
    else
        return true;
}
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+Contract.Entry.TABLE_NAME,null);
        return res;
    }}
