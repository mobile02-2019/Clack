package br.com.digitalhouse.clackapp.service;

import java.util.concurrent.TimeUnit;

import br.com.digitalhouse.clackapp.interfaces.MovieAPI;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    public static final String BASE_URL = "https://api.themoviedb.org/";
    public static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(30, TimeUnit.SECONDS);
            httpClient.readTimeout(30, TimeUnit.SECONDS);
            httpClient.writeTimeout(30, TimeUnit.SECONDS);


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofit;
    }

    public static MovieAPI getMovieAPI(){
        return getRetrofit().create(MovieAPI.class);

    }
}




