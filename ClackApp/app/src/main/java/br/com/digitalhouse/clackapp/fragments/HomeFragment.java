package br.com.digitalhouse.clackapp.fragments;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.clackapp.MainActivity;
import br.com.digitalhouse.clackapp.interfaces.CardMovieClicado;
import br.com.digitalhouse.clackapp.interfaces.ReceptorMovie;
import br.com.digitalhouse.clackapp.interfaces.ServiceListener;
import br.com.digitalhouse.clackapp.model.Movie;
import br.com.digitalhouse.clackapp.R;
import br.com.digitalhouse.clackapp.adapter.RecyclerViewMovieAdapter;
import br.com.digitalhouse.clackapp.model.MovieResponse;
import br.com.digitalhouse.clackapp.model.dao.MovieDAO;

public class HomeFragment extends Fragment implements CardMovieClicado, ServiceListener {

    public static final String MOVIE_TITULO = "movie_titulo";
    private RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4;
    private TextView textView1, textView2, textView3, textView4;
    private Bundle bundle;
    private RecyclerViewMovieAdapter adapter1;
    private RecyclerViewMovieAdapter adapter2;
    private RecyclerViewMovieAdapter adapter3;
    private RecyclerViewMovieAdapter adapter4;
    private ArrayList<String> checados;
    private ReceptorMovie receptorMovie;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        receptorMovie = (ReceptorMovie) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        textView1 = view.findViewById(R.id.textView_1_id);
        textView2 = view.findViewById(R.id.textView_2_id);
        textView3 = view.findViewById(R.id.textView_3_id);
        textView4 = view.findViewById(R.id.textView_4_id);

        Intent intent = getActivity().getIntent();
        bundle = intent.getExtras();

        checados = bundle.getStringArrayList("checados");

        textView1.setText(checados.get(0));
        textView2.setText(checados.get(1));
        textView3.setText(checados.get(2));
        textView4.setText(checados.get(3));

        recyclerView1 = view.findViewById(R.id.recycler_view_id_1);
        recyclerView2 = view.findViewById(R.id.recycler_view_id_2);
        recyclerView3 = view.findViewById(R.id.recycler_view_id_3);
        recyclerView4 = view.findViewById(R.id.recycler_view_id_4);

        adapter1 = new RecyclerViewMovieAdapter(this);

        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        adapter2 = new RecyclerViewMovieAdapter(this);

        LinearLayoutManager manager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        adapter3 = new RecyclerViewMovieAdapter(this);

        LinearLayoutManager manager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        adapter4 = new RecyclerViewMovieAdapter(this);

        LinearLayoutManager manager4 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView1.setLayoutManager(manager1);
        recyclerView1.setAdapter(adapter1);

        recyclerView2.setLayoutManager(manager2);
        recyclerView2.setAdapter(adapter2);

        recyclerView3.setLayoutManager(manager3);
        recyclerView3.setAdapter(adapter3);

        recyclerView4.setLayoutManager(manager4);
        recyclerView4.setAdapter(adapter4);


        List<Integer> ids = new ArrayList<>();
        for (String checado : checados) {
            ids.add(getGenreIdByString(checado));
        }

        MovieDAO dao = new MovieDAO();
        for (Integer i = 0; i < ids.size(); i ++) {
            dao.getMovieList(this, ids.get(i), i + 1);
        }

        return view;
    }

    @Override
    public void onMovieClicado(Movie movie) {
        receptorMovie.receberMovieClicado(movie);

    }

    private Integer getGenreIdByString(String genre) {
        switch (genre) {
            case "Ação":
                return 28;
            case "Animação":
                return 16;
            case "Aventura":
                return 12;
            case "Cinema TV":
                return 10770;
            case "Comédia":
                return 35;
            case "Crime":
                return 80;
            case "Documentário":
                return 99;
            case "Drama":
                return 18;
            case "Família":
                return 10751;
            case "Fantasia":
                return 14;
            case "Faroeste":
                return 37;
            case "Ficção Científica":
                return 878;
            case "Guerra":
                return 10752;
            case "História":
                return 36;
                case "Mistério":
                return 9648;
            case "Música":
                return 10402;
            case "Romance":
                return 10749;
            case "Terror":
                return 27;
            case "Thriller":
                return 53;
            default:
                return 10751;
        }
    }

    @Override
    public void onSuccess(Object object, Integer adapter) {
        MovieResponse movieResponse = (MovieResponse) object;

        switch (adapter) {
            case 1 :
                this.adapter1.setMovieList(movieResponse.getResults());
                break;
            case 2 :
                this.adapter2.setMovieList(movieResponse.getResults());
                break;
            case 3 :
                this.adapter3.setMovieList(movieResponse.getResults());
                break;
            case 4 :
                this.adapter4.setMovieList(movieResponse.getResults());
                break;
        }
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

    }
}