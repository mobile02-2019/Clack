package br.com.digitalhouse.clackapp.interfaces;

import java.util.List;

import br.com.digitalhouse.clackapp.model.Movie;
import br.com.digitalhouse.clackapp.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPI {

    @GET("/3/search/movie?api_key=04b84289e6046e45993b288a7e1fe2dc&language=en-US&include_adult=false")
    Call<MovieResponse> searchMovies(@Query("query") String query);

    @GET("/3/movie/popular?api_key=04b84289e6046e45993b288a7e1fe2dc&language=en-US&include_adult=false")
    Call<MovieResponse> getPopularMovies();

}



