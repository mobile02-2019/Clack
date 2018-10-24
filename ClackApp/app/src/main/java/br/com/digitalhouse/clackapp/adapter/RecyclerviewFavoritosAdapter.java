package br.com.digitalhouse.clackapp.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.digitalhouse.clackapp.FilmeFavoritos;
import br.com.digitalhouse.clackapp.R;

public class RecyclerviewFavoritosAdapter extends RecyclerView.Adapter<RecyclerviewFavoritosAdapter.ViewHolder> {
    private List<FilmeFavoritos> filmeFavoritosList;

    public RecyclerviewFavoritosAdapter(List<FilmeFavoritos> filmeFavoritosList) {
        this.filmeFavoritosList = filmeFavoritosList;
    }

    public List<FilmeFavoritos> getFilmeFavoritosList() {
        return filmeFavoritosList;
    }

    public void setFilmeFavoritosList(List<FilmeFavoritos> filmeFavoritosList) {
        this.filmeFavoritosList = filmeFavoritosList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FilmeFavoritos filmeFavoritos = filmeFavoritosList.get(i);
        viewHolder.bind(filmeFavoritos);

    }

    @Override
    public int getItemCount() {
        return 0;
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
            imagemView = itemView.findViewById(R.drawable.nasceumaestrela);
        }

        public void bind(final FilmeFavoritos filmeFavoritos) {
            titulo.setText(filmeFavoritos.getTitulo());
            sinopse.setText(filmeFavoritos.getSinopse());
            imagemView.setImageDrawable(filmeFavoritos.getImageView());
        }
    }
}
