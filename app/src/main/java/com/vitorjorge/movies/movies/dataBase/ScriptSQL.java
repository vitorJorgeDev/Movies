package com.vitorjorge.movies.movies.dataBase;

/**
 * Created by vitorjorge on 09/03/17.
 */

public class ScriptSQL {

    public static String getCreateContato(){

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE IF NOT EXISTS CONTATO ( ");
        sqlBuilder.append("_id                 INTEGER     NOT NULL ");
        sqlBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("USER                VARCHAR(200), ");
        sqlBuilder.append("PASSWORD            VARCHAR(20) ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }

    public static String getCreatePlayer(){

        StringBuilder sqlBuilder2 = new StringBuilder();

        sqlBuilder2.append("CREATE TABLE IF NOT EXISTS MOVIES ( ");
        sqlBuilder2.append("_id                 INTEGER     NOT NULL ");
        sqlBuilder2.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder2.append("NAME                VARCHAR(200), ");
        sqlBuilder2.append("AGE                VARCHAR(100), ");
        sqlBuilder2.append("NACIONALITY                VARCHAR(200), ");
        sqlBuilder2.append("TYPE            VARCHAR(200) ");
        sqlBuilder2.append("); ");

        return sqlBuilder2.toString();
    }

}