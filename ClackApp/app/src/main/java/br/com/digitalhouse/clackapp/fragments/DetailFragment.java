package br.com.digitalhouse.clackapp.fragments;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import br.com.digitalhouse.clackapp.R;
import br.com.digitalhouse.clackapp.adapter.RecyclerviewFavoritosAdapter;
import br.com.digitalhouse.clackapp.database.FilmesFavoritosContract;
import br.com.digitalhouse.clackapp.database.FilmesFavoritosDbHelper;
import br.com.digitalhouse.clackapp.interfaces.FavoritosListener;
import br.com.digitalhouse.clackapp.interfaces.UpdateMovies;
import br.com.digitalhouse.clackapp.model.FormatarData;
import br.com.digitalhouse.clackapp.model.Movie;
import br.com.digitalhouse.clackapp.service.RetrofitService;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    public static final String MOVIE = "MOVIE";
    private ImageView imagemPost;
    private ImageView share;
    static private Movie filme;
    private ImageView favoritarFilme;
    private ImageButton botaoFechar;
    private FilmesFavoritosDbHelper mDbHelper;
    private SQLiteDatabase db;
    private UpdateMovies listenerUpdate;
    private FavoritosListener favoritosListener;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth firebaseAuth;
//    private List<Movie> movieList = new ArrayList<>();
    private Boolean salvoNoFirebase = false;
    private RecyclerviewFavoritosAdapter recyclerviewFavoritosAdapter;

    public static DetailFragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putSerializable(MOVIE, movie);
        filme = movie;
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listenerUpdate = (UpdateMovies) context;
//        this.filme = (Movie) getArguments().getSerializable(MainActivity.OBJ_FAVORITO);
        this.favoritosListener = (FavoritosListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        mDbHelper = new FilmesFavoritosDbHelper(getContext());
        db = mDbHelper.getWritableDatabase();

        firebaseAuth = FirebaseAuth.getInstance();

        mFirebaseInstance = FirebaseDatabase.getInstance();

        buscarSeExisteNoFirebase(filme);



        imagemPost = view.findViewById(R.id.imagem_act_id);
        share = view.findViewById(R.id.image_compartilhar);
        botaoFechar = view.findViewById(R.id.botao_fechar_id);
        favoritarFilme = view.findViewById(R.id.favoritar_film);
        favoritarFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(salvoNoFirebase){
                    deletarFilmeFb(filme);
                    Toast.makeText(getContext(), "Removido dos Favoritos!", Toast.LENGTH_SHORT).show();

                } else {
                    salvarFilmeFb(filme);
                    Toast.makeText(getContext(), "Favoritado!", Toast.LENGTH_SHORT).show();

                }

            }
        });


        botaoFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });





        share.setOnClickListener(new View.OnClickListener()
       {
            @Override
            public void onClick(View v) {
                onShareClicado(filme);
            }
        });

        filme = (Movie) getArguments().getSerializable(MOVIE);
        String titulo = filme.getNome();
        TextView tituloText = view.findViewById(R.id.titulo_act_id);
        Typeface myCustomFontLogo = Typeface.createFromAsset(getContext().getAssets(), "fonts/LuckiestGuy-Regular.ttf");
        tituloText.setTypeface(myCustomFontLogo);
        tituloText.setText(titulo);


        String descricao = filme.getSinopse();
        TextView descricaoText = view.findViewById(R.id.sinopse_act_id);
        descricaoText.setText(descricao);


        String poster = filme.getPoster();
        Picasso.get().load(RetrofitService.BASE_IMAGE_URL + poster).into(imagemPost);


        String data = filme.getData();
        TextView dataText = view.findViewById(R.id.textView_data_id);
        String dataFormatada = FormatarData.formateData(data);
        dataText.setText("Data de lançamento: " + dataFormatada);

        float nota = filme.getNota();
        RatingBar ratingBar = view.findViewById(R.id.ratingbar_nota_id);
        ratingBar.setRating(nota / 2);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
//        stars.getDrawable(0).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
//        stars.getDrawable(1).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        return view;

    }

    private void buscarSeExisteNoFirebase(final Movie movieBuscado) {

        mFirebaseDatabase = mFirebaseInstance.getReference("favoritos/" + firebaseAuth.getUid());
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            GenericTypeIndicator<Map<String, Movie>> genericTypeIndicator = new GenericTypeIndicator<Map<String, Movie>>() {
                            };
                            Map<String, Movie> mapaApiKeyFilme = dataSnapshot.getValue(genericTypeIndicator);
                            if (mapaApiKeyFilme != null) {
                                salvoNoFirebase=false;
                                favoritarFilme.setImageResource(R.drawable.icon_star_vazia);
                                for (String apiKey : mapaApiKeyFilme.keySet()) {
                                    Movie movie = mapaApiKeyFilme.get(apiKey);
                                    if(movie.getId().equals(movieBuscado.getId())){
                                        favoritarFilme.setImageResource(R.drawable.icon_star_cheia);
                                        salvoNoFirebase = true;
                                        filme.setDatabaseKey(apiKey);
                                        break;
                                    }
                                }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });



    }

    private void salvarFilmeFb(Movie movie) {

//        movieList.add(movie);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        mFirebaseDatabase = mFirebaseInstance.getReference("favoritos/" + firebaseAuth.getUid());

        DatabaseReference push = mFirebaseDatabase.push();

        movie.setDatabaseKey(push.getKey());

        push.setValue(movie);

        buscarSeExisteNoFirebase(movie);//

    }

    private void deletarFilmeFb(Movie movie){

        firebaseAuth = FirebaseAuth.getInstance();

        DatabaseReference firebaseDbReferenceFav = FirebaseDatabase.getInstance().getReference("favoritos/"+firebaseAuth.getUid());

        firebaseDbReferenceFav.child(movie.getDatabaseKey()).removeValue();

        buscarSeExisteNoFirebase(movie);

        favoritarFilme.setImageResource(R.drawable.icon_star_vazia);

    }

    private void salvarFilmeLocal(Movie movie) {
        ContentValues values = new ContentValues();
        values.put(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_IDAPI, movie.getId());
        values.put(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_NAME_TITLE, movie.getNome());
        values.put(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_DATE, movie.getData());
        values.put(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_POSTER, movie.getPoster());
        values.put(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_NOTA, movie.getNota());
        values.put(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_GENERO, movie.getGeneros().toString());
        values.put(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_SINOPSE,movie.getSinopse());
        long newRowId = db.insert(FilmesFavoritosContract.FilmesFavoritosEntry.TABLE_NAME, null, values);
        Toast.makeText(getContext(),"Filme salvo!",Toast.LENGTH_SHORT).show();
        listenerUpdate.updateMovies();
    }



    public void onShareClicado(Movie movie){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT, movie.getNome());
        share.putExtra(Intent.EXTRA_TEXT, "https://image.tmdb.org/t/p/w500" + movie.getPoster());
        startActivity(Intent.createChooser(share, movie.getPoster()));
    }


}
