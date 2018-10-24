package br.com.digitalhouse.clackapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.clackapp.Elementos.Movie;
import br.com.digitalhouse.clackapp.adapter.RecyclerViewPostAdapter;

public class HomeActivity extends AppCompatActivity implements RecyclerViewPostAdapter.CardMovieClicado {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupRecyclerView(findViewById(R.id.recyclerview_movie_id));


    }

    private void setupRecyclerView(View view){

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_movie_id);

        RecyclerViewPostAdapter adapter = new RecyclerViewPostAdapter(createMovieList(),this);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private List<Movie> createMovieList() {
        List<Movie> movieList = new ArrayList<>();

        Movie movie = new Movie();
        movie.setPoster(R.drawable.amigosalienigenas);

        Movie movie1 = new Movie();
        movie.setPoster(R.drawable.casadomedo);

        Movie movie2 = new Movie();
        movie.setPoster(R.drawable.podres);

        return movieList;
    }


    public void filmeEscolhido(View view) {

        Intent intent = new Intent(this,DetailActivity.class);
        startActivity(intent);

    }

    @Override
    public void onMovieClicado(Movie movie) {

    }
}
