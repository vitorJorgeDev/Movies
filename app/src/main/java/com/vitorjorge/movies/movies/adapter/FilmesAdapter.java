package com.vitorjorge.movies.movies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.vitorjorge.movies.movies.R;
import com.vitorjorge.movies.movies.model.Movies;

import java.util.List;

/**
 * Created by vitorjorge on 12/03/17.
 */

public class FilmesAdapter extends RecyclerView.Adapter<FilmesAdapter.FilmesViewHolder> {

   private List<Movies> movies;


    public FilmesAdapter(List<Movies> filmes) {
        this.movies = filmes;
    }

    public class FilmesViewHolder extends RecyclerView.ViewHolder{

        public TextView tvnome, tvAno, tvGenero;

        public FilmesViewHolder(View view) {
            super(view);
            tvnome = (TextView) view.findViewById(R.id.tvNome);
            tvAno = (TextView) view.findViewById(R.id.tvAno);
            tvGenero = (TextView) view.findViewById(R.id.tvGenero);

        }
    }

    @Override
    public FilmesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filme_lista, parent, false);

        return new FilmesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FilmesViewHolder holder, int position){

        Movies filme = movies.get(position);
        holder.tvnome.setText(filme.getName());
        holder.tvGenero.setText(filme.getNacionality());
        holder.tvAno.setText(String.valueOf(filme.getAge()));

    }

    @Override
    public int getItemCount(){
        return  movies.size();

    }

}
