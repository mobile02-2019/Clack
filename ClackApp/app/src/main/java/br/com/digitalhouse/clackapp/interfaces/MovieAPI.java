package br.com.digitalhouse.clackapp.interfaces;

import java.util.List;

import br.com.digitalhouse.clackapp.model.Movie;
import br.com.digitalhouse.clackapp.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {

    @GET("/3/search/movie?api_key=32e03a155eeadffc58dd74f4d0f4669c&language=en-US&include_adult=false")
    Call<MovieResponse> searchMovies(@Query("query") String query);

    @GET("/3/discover/movie?api_key=32e03a155eeadffc58dd74f4d0f4669c&language=en-US&include_adult=false")
    Call<MovieResponse> getByGenreId(@Query("with_genres") Integer id);

    @GET("/3/movie/popular?api_key=32e03a155eeadffc58dd74f4d0f4669c&language=en-US&include_adult=false")
    Call<MovieResponse> getPopularMovies();

    @GET("/3/movie/{movie_id}?api_key=32e03a155eeadffc58dd74f4d0f4669c&language=en-US&include_adult=false")
    Call<Movie> getMovieById (@Path(value = "movie_id") Integer movieId);



    //en-US

}



