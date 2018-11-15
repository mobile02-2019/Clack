package br.com.digitalhouse.clackapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.clackapp.interfaces.CardMovieClicado;
import br.com.digitalhouse.clackapp.model.Movie;
import br.com.digitalhouse.clackapp.R;
import br.com.digitalhouse.clackapp.service.RetrofitService;
import retrofit2.Retrofit;

public class RecyclerViewMovieAdapter extends RecyclerView.Adapter<RecyclerViewMovieAdapter.ViewHolder> {

    private List<Movie> movieList;
    private CardMovieClicado listener;



    public RecyclerViewMovieAdapter(CardMovieClicado listener){
        this.movieList = new ArrayList<>();
        this.listener = listener;

    }

    @NonNull
    @Override
    public RecyclerViewMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_celula_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewMovieAdapter.ViewHolder viewHolder, int position) {
        final Movie movie = movieList.get(position);
        viewHolder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView poster;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.celula_id);

        }

        public void bind(final Movie movie) {

            Picasso.get().load(RetrofitService.BASE_IMAGE_URL+ movie.getPoster()).into(poster);

          poster.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  listener.onMovieClicado(movie);
              }
          });

        }
    }
}
