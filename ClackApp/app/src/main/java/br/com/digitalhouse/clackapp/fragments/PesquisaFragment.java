package br.com.digitalhouse.clackapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.clackapp.adapter.RecyclerViewPesquisaFilmeAdapter;
import br.com.digitalhouse.clackapp.interfaces.CardMovieClicado;
import br.com.digitalhouse.clackapp.model.Movie;
import br.com.digitalhouse.clackapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PesquisaFragment extends Fragment implements CardMovieClicado {
    private RecyclerView recyclerView;
    private RecyclerViewPesquisaFilmeAdapter adapter;
    private SearchView editSearch;
    private List<Movie> movieList;
    private int[] poster;
    private ArrayList<Movie> arrayList = new ArrayList<Movie>();


    public PesquisaFragment() {
        // Required empty public constructor
    }

    @Override // O que devo alterar aqui?
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pesquisa, container, false);
        movieList = createMovieList();
        setupRecyclerView(view);


        return view;
    }


    private void setupRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerview_id);

        RecyclerViewPesquisaFilmeAdapter adapter = new RecyclerViewPesquisaFilmeAdapter(movieList, this);
        recyclerView.setAdapter(adapter);
        int columns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), columns));
    }

    @Override
    public void onMovieClicado(Movie movie) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_main_id, new DetailFragment());
        transaction.commit();

    }

    private List<Movie> createMovieList() {
        List<Movie> movieList = new ArrayList<>();

        Movie movie1 = new Movie();
        movie1.setPoster(R.id.celula_id);
        movieList.add(movie1);

        Movie movie2 = new Movie();
        movie1.setPoster(R.id.celula_id);
        movieList.add(movie2);

        Movie movie3 = new Movie();
        movie1.setPoster(R.id.celula_id);
        movieList.add(movie3);

        Movie movie4 = new Movie();
        movie1.setPoster(R.id.celula_id);
        movieList.add(movie4);

        Movie movie5 = new Movie();
        movie1.setPoster(R.id.celula_id);
        movieList.add(movie5);

        Movie movie6 = new Movie();
        movie1.setPoster(R.id.celula_id);
        movieList.add(movie6);

        Movie movie7 = new Movie();
        movie1.setPoster(R.id.celula_id);
        movieList.add(movie7);

        Movie movie8 = new Movie();
        movie1.setPoster(R.id.celula_id);
        movieList.add(movie8);

        Movie movie9 = new Movie();
        movie1.setPoster(R.id.celula_id);
        movieList.add(movie9);


        return movieList;
    }


}


