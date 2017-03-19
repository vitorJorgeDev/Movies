package com.vitorjorge.movies.movies.network;

import com.vitorjorge.movies.movies.model.User;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by vitorjorge on 09/03/17.
 */

public interface LoginAPI {

    //Endpoint da api que será chamada
    @GET("/v2/58b9b1740f0000b614f09d2f/{id}")
    void getCharacter(@Path("id") int id, Callback<User> user);
}
