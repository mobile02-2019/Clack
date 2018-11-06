package br.com.digitalhouse.clackapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import br.com.digitalhouse.clackapp.interfaces.CardMovieClicado;
import br.com.digitalhouse.clackapp.model.Movie;
import br.com.digitalhouse.clackapp.R;

public class RecyclerViewMovieAdapter3 extends RecyclerView.Adapter<RecyclerViewMovieAdapter3.ViewHolder> implements CardMovieClicado {

    private List<Movie> movieList;
    private CardMovieClicado listener;


    public RecyclerViewMovieAdapter3(List<Movie> movieList){
        this.movieList = movieList;

    }

    @NonNull
    @Override
    public RecyclerViewMovieAdapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_celula_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewMovieAdapter3.ViewHolder viewHolder, int position) {
        Movie movie = movieList.get(position);
        viewHolder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @Override
    public void onMovieClicado(Movie movie) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView poster;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.celula_id);

        }

        public void bind(Movie movie) {

            poster.setImageResource(movie.getPoster());


        }
    }
}


