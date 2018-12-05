package br.com.digitalhouse.clackapp.fragments;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.digitalhouse.clackapp.R;
import br.com.digitalhouse.clackapp.adapter.RecyclerviewFavoritosAdapter;
import br.com.digitalhouse.clackapp.database.FilmesFavoritosContract;
import br.com.digitalhouse.clackapp.database.FilmesFavoritosDbHelper;
import br.com.digitalhouse.clackapp.interfaces.CardMovieClicado;
import br.com.digitalhouse.clackapp.interfaces.FavoritosListener;
import br.com.digitalhouse.clackapp.interfaces.ReceptorMovie;
import br.com.digitalhouse.clackapp.interfaces.RecyclerListenerFavoritos;
import br.com.digitalhouse.clackapp.interfaces.ServiceListener;
import br.com.digitalhouse.clackapp.interfaces.UpdateMovies;
import br.com.digitalhouse.clackapp.model.FilmeFavorito;
import br.com.digitalhouse.clackapp.model.Movie;
import br.com.digitalhouse.clackapp.model.dao.MovieDAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment implements CardMovieClicado,RecyclerListenerFavoritos, ServiceListener {

    private RecyclerView recyclerView;
    private RecyclerviewFavoritosAdapter favoritosAdapter;
    private FilmesFavoritosDbHelper dbHelper;
    private CardView cardFilmeFavorito;
    private ReceptorMovie listener;
    private FavoritosListener listenerFavoritos;
    private List<Movie> listaFavoritos = new ArrayList<>();

    public FavoritosFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listenerFavoritos = (FavoritosListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_favoritos_id);

        favoritosAdapter = new RecyclerviewFavoritosAdapter(listaFavoritos,this);

        recyclerView.setAdapter(favoritosAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dbHelper = new FilmesFavoritosDbHelper(getContext());





        return view;
    }



    @Override
    public void onResume() {
        super.onResume();

        exibirFavoritos();

    }

    private void exibirFavoritos() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
       // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_NAME_TITLE,
                FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_DATE,
                FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_POSTER,
                FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_IDAPI,
                FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_GENERO,
                FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_NOTA,
                FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_SINOPSE,
                FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_IDAPI

        };

        String sortOrder =
                FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_NAME_TITLE + " DESC";
        Cursor cursor = db.query(
                FilmesFavoritosContract.FilmesFavoritosEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );


        while(cursor.moveToNext()) {
            String nome = cursor.getString(
                    cursor.getColumnIndexOrThrow(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_NAME_TITLE));
            String poster = cursor.getString(
                    cursor.getColumnIndexOrThrow(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_POSTER));
            String sinopse = cursor.getString(
                    cursor.getColumnIndexOrThrow(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_SINOPSE));
            float nota = cursor.getFloat(cursor.getColumnIndexOrThrow(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_NOTA));

            Integer idApi = cursor.getInt(cursor.getColumnIndexOrThrow(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_IDAPI));
            //todo ver lista e data


            Movie movie = new Movie();
            movie.setNome(nome);
            movie.setSinopse(sinopse);
            movie.setPoster(poster);
            movie.setNota(nota);
            movie.setId(idApi);
           // movie.setData(data);
            //movie.setGeneros(genero);

            listaFavoritos.add(movie);
        }
        cursor.close();
        favoritosAdapter.setFilmesFaoritos(listaFavoritos);

        }


    @Override
    public void onMovieClicado(Movie movie) {
        listener.receberMovieClicado(movie);

    }

    @Override
    public void onFavoritoClicado(Integer movieID) {
        MovieDAO movieDAO = new MovieDAO();
        movieDAO.getMovieById(this,movieID, 1);
    }

    @Override
    public void onSuccess(Object object, Integer adapter) {
        Movie movie = (Movie) object;
        listenerFavoritos.iniciarFragmentDetalheFavorito(movie);
    }

    @Override
    public void onError(Throwable throwable) {

    }
}
