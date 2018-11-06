package br.com.digitalhouse.clackapp.model.dao;

import android.telecom.Call;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.clackapp.model.Movie;
import br.com.digitalhouse.clackapp.service.RetrofitService;
import retrofit2.Callback;

public class MovieDAO {

    List<Movie> postList = new ArrayList<>();

    retrofit2.Call<List<Movie>> call = RetrofitService.getPostApi().getPosts();

        call.enqueue(new Callback<List<Post>>() {

    }


    }
