package br.com.digitalhouse.clackapp.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.clackapp.model.FilmeFavorito;
import br.com.digitalhouse.clackapp.R;
import br.com.digitalhouse.clackapp.model.Movie;
import br.com.digitalhouse.clackapp.service.RetrofitService;

public class RecyclerviewFavoritosAdapter extends RecyclerView.Adapter<RecyclerviewFavoritosAdapter.ViewHolder> {

    private List<Movie> filmeFavoritosList = new ArrayList<>();

    public RecyclerviewFavoritosAdapter(List<Movie> filmeFavoritosList) {
        this.filmeFavoritosList = filmeFavoritosList;
        notifyDataSetChanged();

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.modelo_recyclerview_favoritos, viewGroup, false);
        //if (listavazia) colocar background visible
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Movie filmeFavoritos = filmeFavoritosList.get(i);
        viewHolder.bind(filmeFavoritos);

    }

    @Override
    public int getItemCount() {
        return filmeFavoritosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titulo;
        private TextView sinopse;
        private ImageView imagemView;

        @SuppressLint("ResourceType")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.TituloPrimeiroFilme_id);
            sinopse = itemView.findViewById(R.id.sinopsePrimeiroFile_id);
            imagemView = itemView.findViewById(R.id.primeiro_filme_id);
        }

        public void bind(final Movie movie) {
            titulo.setText(movie.getNome());
            sinopse.setText(movie.getSinopse());
            Picasso.get().load(RetrofitService.BASE_IMAGE_URL+ movie.getPoster()).into(imagemView);


        }
    }
}
