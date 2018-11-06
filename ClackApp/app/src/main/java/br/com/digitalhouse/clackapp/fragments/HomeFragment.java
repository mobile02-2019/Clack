package br.com.digitalhouse.clackapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.clackapp.interfaces.CardMovieClicado;
import br.com.digitalhouse.clackapp.interfaces.ServiceListener;
import br.com.digitalhouse.clackapp.model.Movie;
import br.com.digitalhouse.clackapp.R;
import br.com.digitalhouse.clackapp.adapter.RecyclerViewMovieAdapter;
import br.com.digitalhouse.clackapp.adapter.RecyclerViewMovieAdapter2;
import br.com.digitalhouse.clackapp.adapter.RecyclerViewMovieAdapter3;
import br.com.digitalhouse.clackapp.adapter.RecyclerViewMovieAdapter4;
import br.com.digitalhouse.clackapp.model.MovieResponse;
import br.com.digitalhouse.clackapp.model.dao.MovieDAO;

public class HomeFragment extends Fragment implements CardMovieClicado, ServiceListener {

    public static final String MOVIE_TITULO = "movie_titulo";
    private RecyclerView recyclerView,recyclerView2,recyclerView3,recyclerView4;
    private RecyclerViewMovieAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MovieDAO dao = new MovieDAO();
        dao.getMovieList(this);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_id);
        recyclerView2 = view.findViewById(R.id.recycler_view_id_2);
        recyclerView3 = view.findViewById(R.id.recycler_view_id_3);
        recyclerView4 = view.findViewById(R.id.recycler_view_id_4);

        adapter = new RecyclerViewMovieAdapter();

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);

        RecyclerViewMovieAdapter2 adapter2 = new RecyclerViewMovieAdapter2(getListMovie());

        LinearLayoutManager manager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);

        RecyclerViewMovieAdapter3 adapter3 = new RecyclerViewMovieAdapter3(getListMovie());

        LinearLayoutManager manager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);

        RecyclerViewMovieAdapter4 adapter4 = new RecyclerViewMovieAdapter4(getListMovie());

        LinearLayoutManager manager4 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        recyclerView2.setLayoutManager(manager2);
        recyclerView2.setAdapter(adapter2);

        recyclerView3.setLayoutManager(manager3);
        recyclerView3.setAdapter(adapter3);

        recyclerView4.setLayoutManager(manager4);
        recyclerView4.setAdapter(adapter4);


        return view;
    }



    private List<Movie> getListMovie() {

        List<Movie> movieList = new ArrayList<>();

        Movie movie = new Movie();
        movie.setPosterId(R.drawable.cover_podresdericos);
        movieList.add(movie);

        Movie movie1 = new Movie();
        movie1.setPosterId(R.drawable.cover_amigosalienigenas);
        movieList.add(movie1);

        Movie movie2 = new Movie();
        movie2.setPosterId(R.drawable.cover_oscrimesdegrindelwald);
        movieList.add(movie2);

        Movie movie3 = new Movie();
        movie3.setPosterId(R.drawable.cover_casadomedo);
        movieList.add(movie3);

        return movieList;
    }


    @Override
    public void onMovieClicado(Movie movie) {

        Bundle bundle = new Bundle();

        bundle.putString(MOVIE_TITULO,movie.getNome());

        Intent intent = new Intent(getContext(), DetailFragment.class);
        intent.putExtras(bundle);
        startActivity(intent);


    }


    @Override
    public void onSuccess(Object object) {
        MovieResponse movieResponse = (MovieResponse) object;
        ArrayList<Movie> movieList = movieResponse.getResults();
        //TODO filtrar a lista de acordo com o genero

        List<Movie> filmesFiltrados = new ArrayList<>();

        for (Movie movie : movieList) {
            for (Integer genreId : movie.getGeneros()) {
                if(genreId == 28){ // filtro por ação
                    filmesFiltrados.add(movie);
                }
            }
        }
        adapter.setMovieList(filmesFiltrados);

    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

    }
}
