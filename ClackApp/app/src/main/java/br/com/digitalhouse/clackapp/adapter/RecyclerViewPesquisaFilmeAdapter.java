package br.com.digitalhouse.clackapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import br.com.digitalhouse.clackapp.R;
import br.com.digitalhouse.clackapp.interfaces.CardMovieClicado;
import br.com.digitalhouse.clackapp.model.Movie;

public class RecyclerViewPesquisaFilmeAdapter extends RecyclerView.Adapter<RecyclerViewPesquisaFilmeAdapter.ViewHolder> {
    private List<Movie> movieList;
    private CardMovieClicado listener;

    public RecyclerViewPesquisaFilmeAdapter(List<Movie> movieList, CardMovieClicado listener) {
        this.movieList = movieList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerViewPesquisaFilmeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.recyclerview_celula_item , viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewPesquisaFilmeAdapter.ViewHolder viewHolder, int i) {
        final Movie movie = movieList.get(i);
        viewHolder.bind(movie);
        viewHolder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMovieClicado(movie);

            }
        });



    }

    @Override
    public int getItemCount() {
        return movieList.size();


    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView poster;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.celula_id);



        }

        public void bind(final Movie movie) {



        }




        }
    }




