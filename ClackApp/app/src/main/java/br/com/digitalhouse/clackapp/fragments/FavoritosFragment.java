package br.com.digitalhouse.clackapp.fragments;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
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
import br.com.digitalhouse.clackapp.model.FilmeFavorito;
import br.com.digitalhouse.clackapp.model.Movie;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment {

    private RecyclerView recyclerView;

    public FavoritosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_favoritos_id);

        RecyclerviewFavoritosAdapter adapter = new RecyclerviewFavoritosAdapter((getFilmeFavoritosList()));

        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }


    private List<Movie> getFilmeFavoritosList(){
        List<Movie> filmeFavoritosList = new ArrayList<>();

        FilmesFavoritosDbHelper mDbHelper = new FilmesFavoritosDbHelper(getContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_NAME_TITLE,
                FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_DATE,
                FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_POSTER,
                FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_GENERO,
                FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_NOTA
        };

// Filter results WHERE "title" = 'My Title'
        String selection = FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
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

            //adicionar um campo por vez
            //leio e add o nome
            String nome = cursor.getString(
                    cursor.getColumnIndexOrThrow(FilmesFavoritosContract.FilmesFavoritosEntry._ID));
            String poster = cursor.getString(
                    cursor.getColumnIndexOrThrow(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_POSTER));
            String sinopse = cursor.getString(
                    cursor.getColumnIndexOrThrow(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_SINOPSE));
            float nota = cursor.getFloat(cursor.getColumnIndexOrThrow(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_NOTA));

           // List<Integer> genero = cursor.(cursor.getColumnIndexOrThrow(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_GENERO));

          //  String data = cursor.getString(cursor.getColumnIndexOrThrow(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_DATE));



            Movie movie = new Movie();
            movie.setNome(nome);
            movie.setPoster(poster);
           // movie.setGeneros(genero);
            movie.setNota(nota);
            movie.setSinopse(sinopse);
            //movie.setData(data);



            filmeFavoritosList.add(movie);


        }
        cursor.close();

        


        return filmeFavoritosList;
    }


}
