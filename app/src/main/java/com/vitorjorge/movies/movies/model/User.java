package com.vitorjorge.movies.movies.model;

/**
 * Created by vitorjorge on 09/03/17.
 */

public class User {
    public static String TABLE = "CONTATO";
    public static String USER = "USER";
    public static String PASSWORD = "PASSWORD";
    public static String ID = "_id";
    private long id;

    public User(){

        id = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String usuario;
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

