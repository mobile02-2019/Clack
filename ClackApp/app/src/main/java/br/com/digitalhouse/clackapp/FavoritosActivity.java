package br.com.digitalhouse.clackapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.clackapp.adapter.RecyclerviewFavoritosAdapter;

public class FavoritosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modelo_Recyclerview_favoritos);

        recyclerView = findViewById(R.id.Recyclerview_id);

        RecyclerviewFavoritosAdapter adapter = new RecyclerviewFavoritosAdapter((getfilmeFavoritosList));

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }


    private List<FilmeFavoritos> getFilmeFavoritosList(){
        List<FilmeFavoritos> filmeFavoritosList = new ArrayList<>();

        FilmeFavoritos filmeFavoritos = new FilmeFavoritos();
        filmeFavoritos.setImageView(R.drawable.nasceumaestrela);
        filmeFavoritos.setTitulo("NASCE UMA ESTRELA ");
        filmeFavoritos.setSinopse("Nesta releitura da trágica história de amor, Cooper interpreta o experiente músico Jackson Maine, que descobre a artista desconhecida Ally (Gaga), por quem se apaixona.…");
        filmeFavoritosList.add(filmeFavoritos);

        FilmeFavoritos filmeFavoritos2 = new FilmeFavoritos();
        filmeFavoritos2.setImageView(R.drawable.amigosalienigenas);
        filmeFavoritos2.setTitulo("AMIGOS ALIENIGENAS");
        filmeFavoritos2.setSinopse("A vida de Louis, um menino de doze anos, muda completamente quando a nave espacial de três alienígenas cai nos fundos do quintal de sua casa....");
        filmeFavoritosList.add(filmeFavoritos2);

        FilmeFavoritos filmeFavoritos3 = new FilmeFavoritos();
        filmeFavoritos3.setImageView(R.drawable.casadomedo);
        filmeFavoritos3.setTitulo("CASA DO MEDO");
        filmeFavoritos3.setSinopse("Pauline acaba de herdar uma casa de sua tia e então decide morar lá com suas duas filhas, Beth e Vera. Mas, logo na primeira noite, o lugar é atacado por violentos…");
        filmeFavoritosList.add(filmeFavoritos3);

        return filmeFavoritosList;
    }



}
