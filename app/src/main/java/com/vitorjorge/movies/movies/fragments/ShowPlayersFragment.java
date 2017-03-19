package com.vitorjorge.movies.movies.fragments;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vitorjorge.movies.movies.R;
import com.vitorjorge.movies.movies.adapter.FilmesAdapter;
import com.vitorjorge.movies.movies.dataBase.Database;
import com.vitorjorge.movies.movies.decoration.DividerItemDecoration;
import com.vitorjorge.movies.movies.domain.RepositoryUser;
import com.vitorjorge.movies.movies.listener.ClickListener;
import com.vitorjorge.movies.movies.listener.RecyclerTouchListener;
import com.vitorjorge.movies.movies.model.Movies;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowPlayersFragment extends Fragment {


    private Database database;
    private SQLiteDatabase conn;
    private ArrayList<Movies> filmes;
    private RecyclerView recyclerView;
    private FilmesAdapter mAdapter;
    private RepositoryUser repositoryUser;
    private ArrayList<Movies> arrayList = new ArrayList<>();
    private static String DELETEMESSAGE;
//    private  String title, message;




    public ShowPlayersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        filmes = new ArrayList<>();
        final View rootView = inflater.inflate(R.layout.fragment_show_players, container, false);
        DELETEMESSAGE = getResources().getString(R.string.tst_deleteMessage);



        // Faz o bind com o Layout
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvFilmes);

        try{
            database = new Database(rootView.getContext());
            conn = database.getWritableDatabase();
            repositoryUser = new RepositoryUser(conn);


        }catch (Exception e){

            Log.i("DESCRITION", "=" + e.getMessage());

            e.printStackTrace();
        }

                mAdapter = new FilmesAdapter(filmes);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());

                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                recyclerView.addItemDecoration(new DividerItemDecoration(rootView.getContext(), LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(mAdapter);


                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(rootView.getContext(), recyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        Movies filme = filmes.get(position);
                        String nome = (filme.getName());
                        String idade = (filme.getAge());
                        String nacionality = (filme.getNacionality());
                        String tipo = (filme.getType());
                        Long id = (filme.getId());

                        Bundle i = new Bundle();
                        i.putString("name", nome);
                        i.putString("age", idade);
                        i.putString("nacionality", nacionality);
                        i.putString("type", tipo);
                        i.putLong("id", id);

                        RegisterFragment frag = new RegisterFragment();
                        frag.setArguments(i);
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container
                                        ,  frag)
                                .commit();

                    }

                    @Override
                    public void onLongClick(View view, int position) {


                        // Para excluir com o longClick
//                        Movies filme = filmes.get(position);
//
//                        excluirPlayer(filme);

                    }
                }));

        redeemMovies();


        // Inflate the layout for this fragment
        return rootView;

    }

    private void redeemMovies() {


        arrayList = repositoryUser.searchPlayer();
        int count = arrayList.size();

        for (int i = 0; i<count; i++ ){

            Movies movies = arrayList.get(i);
            Movies newMovies = new Movies();

            newMovies.setName(movies.getName());
            newMovies.setAge(movies.getAge());
            newMovies.setType(movies.getType());
            newMovies.setNacionality(movies.getNacionality());
            newMovies.setId(movies.getId());

            filmes.add(newMovies);

        }

       mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (conn != null) {

            conn.close();

        }
    }


//    private void excluirPlayer(final Movies filme){
//
//        //
//        new AlertDialog.Builder(getContext())
//                .setTitle(title)
//                .setMessage(message)
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        try{
//
//                            repositoryUser.remove(filme.getId());
//                            arrayList = repositoryUser.searchPlayer();
//
//                        }catch (Exception e){
//
//                            Log.i("DESCRIPTION", "=" + e.getMessage());
//                        }
//
//                        filmes.clear();
//                        redeemMovies();
//
//                    }
//                })
//                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        return;
//                    }
//                })
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .show();
//        //
//
//    }

}
