package br.com.digitalhouse.clackapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import br.com.digitalhouse.clackapp.Elementos.Movie;
import br.com.digitalhouse.clackapp.R;
import br.com.digitalhouse.clackapp.adapter.ListViewPesquisaFilmeAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PesquisaFragment extends Fragment implements SearchView.OnQueryTextListener{

    private ListView list; //recycler
    private ListViewPesquisaFilmeAdapter adapter;
    private SearchView editSearch;
    private String[] movieList;
    private int[] poster;
    private ArrayList<Movie> arrayList = new ArrayList<Movie>();

    /*private RecyclerView recyclerView;
    private List<Movie> movieList;
    private RecyclerViewMovieAdapter adapter;
    private Movie movie;
    private SearchView editSearch;
    private String[] nomeFilmes; //esse é só para ver se consigo fazer, pois os filmes deveriam vir da api
    private ArrayList<Movie> arrayList;
    private Context context;*/


    public PesquisaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pesquisa, container, false);

        movieList = new String[]{"Nasce uma Estrela", "Amigos Alienigenas","Casa do Medo"
         };

        poster = new int[]{R.drawable.cover_nasceumaestrela,R.drawable.cover_amigosalienigenas,R.drawable.cover_casadomedo
        };

        list = (ListView) view.findViewById (R.id.listview_id);

        for (int i = 0; i < movieList.length; i++) {
            Movie movie = new Movie(movieList[i], poster[i]);
            // Binds all strings into an array
            arrayList.add(movie);
        }

        //Passando o resultado para a Classe ListView
        adapter = new ListViewPesquisaFilmeAdapter(getContext(), arrayList);

        list.setAdapter(adapter);

        editSearch = view.findViewById(R.id.search_id);
        editSearch.setOnQueryTextListener(this);

        return view;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}
