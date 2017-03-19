package com.vitorjorge.movies.movies.domain;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vitorjorge.movies.movies.model.Movies;
import com.vitorjorge.movies.movies.model.User;
import java.util.ArrayList;

/**
 * Created by vitorjorge on 09/03/17.
 */

public class RepositoryUser {
    private SQLiteDatabase conn;


    public RepositoryUser(SQLiteDatabase conn){
        this.conn = conn;


    }

    public void insert(User user){

        ContentValues values =  new ContentValues();
        values.put(user.USER, user.getUsuario());
        values.put(user.PASSWORD, user.getSenha());

        conn.insertOrThrow(user.TABLE, null, values);

    }

    public void insertPlayer(Movies movies){

        ContentValues values2 =  new ContentValues();
        values2.put(movies.NAME, movies.getName());
        values2.put(movies.AGE, movies.getAge());
        values2.put(movies.NACIONALITY, movies.getNacionality());
        values2.put(movies.TYPE, movies.getType());

        conn.insertOrThrow(movies.TABLES, null, values2);

    }


    public ArrayList<User> serchUsers(){

        ArrayList<User> arrayList = new ArrayList<>();

        Cursor cursor = conn.query(User.TABLE, null, null, null, null, null, null);

        if (cursor.getCount() > 0){

            cursor.moveToFirst();

            do{

                User user = new User();
                user.setUsuario(cursor.getString(cursor.getColumnIndex(User.USER)));
                user.setSenha(cursor.getString(cursor.getColumnIndex(User.PASSWORD)));
                user.setId(cursor.getLong(cursor.getColumnIndex(User.ID)));
                arrayList.add(user);

            }while (cursor.moveToNext());

        }

        if(arrayList.isEmpty()){
            return null;
        }else{
            return arrayList;
        }


    }


    public ArrayList<Movies> searchPlayer(){

        ArrayList<Movies> arrayList = new ArrayList<>();

        Cursor cursor = conn.query(Movies.TABLES, null, null, null, null, null, null);

        if (cursor.getCount() > 0){

            cursor.moveToFirst();

            do{

                Movies movies = new Movies();
                movies.setName(cursor.getString(cursor.getColumnIndex(com.vitorjorge.movies.movies.model.Movies.NAME)));
                movies.setAge(cursor.getString(cursor.getColumnIndex(Movies.AGE)));
                movies.setNacionality(cursor.getString(cursor.getColumnIndex(com.vitorjorge.movies.movies.model.Movies.NACIONALITY)));
                movies.setType(cursor.getString(cursor.getColumnIndex(com.vitorjorge.movies.movies.model.Movies.TYPE)));
                movies.setId(cursor.getLong(cursor.getColumnIndex(com.vitorjorge.movies.movies.model.Movies.ID)));



                arrayList.add(movies);


            }while (cursor.moveToNext());

        }

        return arrayList;

    }

    public void remove(long id){

        conn.delete(Movies.TABLES, "_id = ?", new String[]{String.valueOf(id)});
    }

    public void removeUser(long id){

        conn.delete(User.TABLE, "_id = ?", new String[]{String.valueOf(id)});
    }

    public void change(Movies movies){

        ContentValues values = new ContentValues();
        values.put(com.vitorjorge.movies.movies.model.Movies.NAME, movies.getName());
        values.put(com.vitorjorge.movies.movies.model.Movies.NACIONALITY, movies.getNacionality());
        values.put(com.vitorjorge.movies.movies.model.Movies.AGE, movies.getAge());
        values.put(com.vitorjorge.movies.movies.model.Movies.TYPE, movies.getType());

        conn.update(Movies.TABLES, values, "_id = ?", new String[]{ String.valueOf( movies.getId() ) }  );

    }

}

