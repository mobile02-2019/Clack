package br.com.digitalhouse.clackapp.fragments;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.clackapp.adapter.RecyclerViewPesquisaFilmeAdapter;
import br.com.digitalhouse.clackapp.interfaces.CardMovieClicado;
import br.com.digitalhouse.clackapp.interfaces.ReceptorMovie;
import br.com.digitalhouse.clackapp.interfaces.ServiceListener;
import br.com.digitalhouse.clackapp.model.Movie;
import br.com.digitalhouse.clackapp.R;
import br.com.digitalhouse.clackapp.model.MovieResponse;
import br.com.digitalhouse.clackapp.model.dao.MovieDAO;


/**
 * A simple {@link Fragment} subclass.
 */
public class PesquisaFragment extends Fragment implements CardMovieClicado,ServiceListener {
    private RecyclerView recyclerView;
    private RecyclerViewPesquisaFilmeAdapter adapter;
    private SearchView editSearch;
    private List<Movie> movieList = new ArrayList<>();
    private ProgressBar progressBar;
    private ReceptorMovie listener;


    public PesquisaFragment() {
        // Required empty public constructor
    }

    @Override // O que devo alterar aqui?
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (ReceptorMovie) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pesquisa, container, false);
        //movieList = createMovieList();


        progressBar = view.findViewById(R.id.progressbar_id);
        progressBar.setVisibility(View.INVISIBLE);




        editSearch = view.findViewById(R.id.search_id);
        editSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(query != ""){
                    callMovieService(query);
                    progressBar.setVisibility(View.VISIBLE);

                }else {
                    progressBar.setVisibility(View.INVISIBLE);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(newText != ""){
                    progressBar.setVisibility(View.VISIBLE);
                    callMovieService(newText);
                }else {
                    progressBar.setVisibility(View.INVISIBLE);
                }

                //Toast.makeText(getContext(), newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        setupRecyclerView(view);

        return view;

    }


    private void setupRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerview_id);

        adapter = new RecyclerViewPesquisaFilmeAdapter(movieList, this);
        recyclerView.setAdapter(adapter);
        int columns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), columns));




    }

    @Override
    public void onMovieClicado(Movie movie) {
        listener.receberMovieClicado(movie);



    }

    private void callMovieService(String query){
        MovieDAO dao = new MovieDAO();
        dao.getSearchList(this, query, 1);
    }

    @Override
    public void onSuccess(Object object, Integer adapterId) {
        MovieResponse response = (MovieResponse) object;
        List<Movie> movieList = response.getResults();
        adapter.setMovieList(movieList);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onError(Throwable throwable) {
        progressBar.setVisibility(View.GONE);

    }
}



