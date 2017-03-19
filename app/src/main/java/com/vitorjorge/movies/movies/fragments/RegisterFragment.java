package com.vitorjorge.movies.movies.fragments;


import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.vitorjorge.movies.movies.R;
import com.vitorjorge.movies.movies.dataBase.Database;
import com.vitorjorge.movies.movies.domain.RepositoryUser;
import com.vitorjorge.movies.movies.model.Movies;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private Database database;
    private SQLiteDatabase conn;
    private RepositoryUser repositoryUser;
    private Movies movies;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        final Button cadastrar = (Button) rootView.findViewById(R.id.btn_registerPlayerId);
        final EditText name = (EditText) rootView.findViewById(R.id.edt_playerNameId);
        final EditText age = (EditText) rootView.findViewById(R.id.edt_playerAgeId);
        final EditText nacionality = (EditText) rootView.findViewById(R.id.edt_playerNacionalityId);
        final EditText type = (EditText) rootView.findViewById(R.id.edt_playerTypeId);
        final Button excluir = (Button) rootView.findViewById(R.id.buttonExluir);

        final String upDateText = getResources().getString(R.string.btn_update_Register);
        final String title = getResources().getString(R.string.ad_setTitle_Register);
        final String message = getResources().getString(R.string.ad_setMessage_Register);



        try{
            database = new Database(rootView.getContext());
            conn = database.getWritableDatabase();
            repositoryUser = new RepositoryUser(conn);


        }catch (Exception e){

            Log.i("DESCRITION", "-" + e.getMessage());

            e.printStackTrace();
        }


        // TODO
        final Bundle bundle = this.getArguments();
        if(bundle != null) {


            movies = new Movies();

            String nome = bundle.getString("name");
            String idade = bundle.getString("age");
            String nacionality1 = bundle.getString("nacionality");
            String tipo = bundle.getString("type");
            name.setText(nome);
            age.setText(idade);
            nacionality.setText(nacionality1);
            type.setText(tipo);
            cadastrar.setText(upDateText);
            excluir.setVisibility(View.VISIBLE);

            excluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    //TODO


                        new AlertDialog.Builder(getContext())
                                .setTitle(title)
                                .setMessage(message)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        Long id = bundle.getLong("id");

                                        removeMovie(id);
                                        name.setText("");
                                        age.setText("");
                                        nacionality.setText("");
                                        type.setText("");
                                        showFragmentShowPlayers();

                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        return;
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setCancelable(false)
                                .show();


                }
            });

        }

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cadastrar.getText().equals("Atualizar") || cadastrar.getText().equals("Update")){

                    movies.setName(name.getText().toString());
                    movies.setAge(age.getText().toString());
                    movies.setNacionality(nacionality.getText().toString());
                    movies.setType(type.getText().toString());
                    movies.setId(bundle.getLong("id"));

                    try{

                        repositoryUser.change(movies);

                    }catch (Exception e){

                        Log.i("DESCRIPTION", "=" + e.getMessage());

                    }

                }else{


                    Movies movies = new Movies();

                    movies.setName(name.getText().toString());
                    movies.setAge(age.getText().toString());
                    movies.setNacionality(nacionality.getText().toString());
                    movies.setType(type.getText().toString());

                    insertPlayerRegister(movies);
                    name.setText("");
                    age.setText("");
                    nacionality.setText("");
                    type.setText("");

                }

                showFragmentShowPlayers();

            }
        });


        return rootView;
    }


    public void insertPlayerRegister(Movies movies){
        try{
            if(movies.getId() == 0){

                repositoryUser.insertPlayer(movies);

            }

        }catch (Exception e ){

            Log.i("DESCRITION", "=" + e.getMessage());
        }

    }


    private void removeMovie(Long id){
        try{

            repositoryUser.remove(id);


        }catch (Exception e){

            Log.i("DESCRIPTION", "=" + e.getMessage());
        }


    }

    private void showFragmentShowPlayers(){

        ShowPlayersFragment fragment2 = new ShowPlayersFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment2);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (conn != null) {

            conn.close();

        }
    }

}

