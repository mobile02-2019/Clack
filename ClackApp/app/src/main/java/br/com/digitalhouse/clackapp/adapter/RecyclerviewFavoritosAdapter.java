package br.com.digitalhouse.clackapp.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.digitalhouse.clackapp.interfaces.RecyclerListenerFavoritos;
import br.com.digitalhouse.clackapp.model.FilmeFavorito;
import br.com.digitalhouse.clackapp.R;
import br.com.digitalhouse.clackapp.model.FormatarData;
import br.com.digitalhouse.clackapp.model.Movie;
import br.com.digitalhouse.clackapp.service.RetrofitService;

public class RecyclerviewFavoritosAdapter extends RecyclerView.Adapter<RecyclerviewFavoritosAdapter.ViewHolder> {

    private List<Movie> filmeFavoritosList = new ArrayList<>();
    private RecyclerListenerFavoritos recyclerListenerFavoritos;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mref;

    public RecyclerviewFavoritosAdapter(List<Movie> filmeFavoritosLists, RecyclerListenerFavoritos listenerFavoritos) {
        this.filmeFavoritosList = filmeFavoritosLists;
        this.recyclerListenerFavoritos = listenerFavoritos;

    }

    public RecyclerviewFavoritosAdapter() {

    }

    public void setFilmeFavoritosList(List<Movie> filmeFavoritosList) {
        this.filmeFavoritosList = filmeFavoritosList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.modelo_recyclerview_favoritos, viewGroup, false);

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

    public void deletarFilmeFavorito (Movie movie){
        mAuth = FirebaseAuth.getInstance();
        filmeFavoritosList.remove(movie);
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("favoritos/"+mAuth.getUid());

        myRef.child(movie.getDatabaseKey()).removeValue();

        notifyDataSetChanged();
    }

    public void setFilmesFavoritos(List<Movie> filmeFavoritosList) {
        this.filmeFavoritosList = filmeFavoritosList;
        notifyDataSetChanged();


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titulo;
        private TextView nota;
        private ImageView imagemView;
        private TextView data;
        private CardView cardFavorito;
        private ImageView deletar;

        @SuppressLint("ResourceType")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.TituloPrimeiroFilme_id);
            nota = itemView.findViewById(R.id.textViewCard_nota_id);
            data = itemView.findViewById(R.id.textViewCard_data_id);
            imagemView = itemView.findViewById(R.id.primeiro_filme_id);
            cardFavorito = itemView.findViewById(R.id.cardView_favorito);
            deletar = itemView.findViewById(R.id.imageview_deletar_favoritos_id);
        }

        public void bind(final Movie movie) {
            titulo.setText(movie.getNome());
            nota.setText("Nota Média: " + movie.getNota());

            String teste = movie.getData();
            if (movie.getData() != null) {
//                data.setText(movie.getData());
                String dataFilme = movie.getData();

                data.setText("Data de lançamento:\n        " + FormatarData.formateData(dataFilme));


//                data = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            }
            Picasso.get().load(RetrofitService.BASE_IMAGE_URL + movie.getPoster()).into(imagemView);
            cardFavorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerListenerFavoritos.onFavoritoClicado(movie.getId());
                }
            });

            deletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletarFilmeFavorito(movie);
                }
            });



        }
    }
}
