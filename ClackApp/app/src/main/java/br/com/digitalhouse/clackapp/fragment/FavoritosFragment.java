package br.com.digitalhouse.clackapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.clackapp.R;
import br.com.digitalhouse.clackapp.adapter.RecyclerviewFavoritosAdapter;
import br.com.digitalhouse.clackapp.elementos.FilmeFavorito;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment {

    private RecyclerView recyclerView;

    public FavoritosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_favoritos_id);

        RecyclerviewFavoritosAdapter adapter = new RecyclerviewFavoritosAdapter((getFilmeFavoritosList()));

        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }


    private List<FilmeFavorito> getFilmeFavoritosList(){
        List<FilmeFavorito> filmeFavoritosList = new ArrayList<>();

        FilmeFavorito filmeFavoritos = new FilmeFavorito();
        filmeFavoritos.setImageView(R.drawable.cover_nasceumaestrela);
        filmeFavoritos.setTitulo("NASCE UMA ESTRELA ");
        filmeFavoritos.setSinopse("Nesta releitura da trágica história de amor, Cooper interpreta o experiente músico Jackson Maine, que descobre a artista desconhecida Ally (Gaga), por quem se apaixona.…");
        filmeFavoritosList.add(filmeFavoritos);

        FilmeFavorito filmeFavoritos2 = new FilmeFavorito();
        filmeFavoritos2.setImageView(R.drawable.cover_amigosalienigenas);
        filmeFavoritos2.setTitulo("AMIGOS ALIENIGENAS");
        filmeFavoritos2.setSinopse("A vida de Louis, um menino de doze anos, muda completamente quando a nave espacial de três alienígenas cai nos fundos do quintal de sua casa....");
        filmeFavoritosList.add(filmeFavoritos2);

        FilmeFavorito filmeFavoritos3 = new FilmeFavorito();
        filmeFavoritos3.setImageView(R.drawable.cover_casadomedo);
        filmeFavoritos3.setTitulo("CASA DO MEDO");
        filmeFavoritos3.setSinopse("Pauline acaba de herdar uma casa de sua tia e então decide morar lá com suas duas filhas, Beth e Vera. Mas, logo na primeira noite, o lugar é atacado por violentos…");
        filmeFavoritosList.add(filmeFavoritos3);

        FilmeFavorito filmeFavoritos4 = new FilmeFavorito();
        filmeFavoritos4.setImageView(R.drawable.cover_oscrimesdegrindelwald);
        filmeFavoritos4.setTitulo("OS CRIMES DE GRINDELWAD");
        filmeFavoritos4.setSinopse("Newt Scamander reencontra os queridos amigos Tina Goldstein, Queenie Goldstein e Jacob Kowalski. Ele é recrutado pelo seu antigo professor em Hogwarts, Alvo Dumbledore, para enfrentar o terrível bruxo das trevas…");
        filmeFavoritosList.add(filmeFavoritos4);

        return filmeFavoritosList;
    }


}
