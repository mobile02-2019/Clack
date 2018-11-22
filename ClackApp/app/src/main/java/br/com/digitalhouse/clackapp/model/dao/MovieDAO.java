package br.com.digitalhouse.clackapp.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.clackapp.interfaces.ServiceListener;
import br.com.digitalhouse.clackapp.model.Movie;
import br.com.digitalhouse.clackapp.model.MovieResponse;
import br.com.digitalhouse.clackapp.service.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;


public class MovieDAO {



    //Como está buscando apenas da API (não de um arquivo) pode ser void o método getMovieList;

    public void getMovieList(final ServiceListener listener, Integer id, final Integer adaptadorNumber) {
        List<Movie> postList = new ArrayList<>();

        //Chamada para API
        Call<MovieResponse> call = RetrofitService.getMovieAPI().getByGenreId(id);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.body() != null){
                    listener.onSuccess(response.body(), adaptadorNumber);
                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                listener.onError(t);

            }

        });


    }

    public void getSearchList (final ServiceListener listener, String query, final Integer adaptador){
        List<Movie> postList = new ArrayList<>();

        Call<MovieResponse> call = RetrofitService.getMovieAPI().searchMovies(query);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.body() != null){
                    listener.onSuccess(response.body(), adaptador);
                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                listener.onError(t);

            }

        });





    }


}
