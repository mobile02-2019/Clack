package br.com.digitalhouse.clackapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.digitalhouse.clackapp.R;
import br.com.digitalhouse.clackapp.model.Movie;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    public static final String MOVIE = "MOVIE";
    private ImageView imagemPost;
    private ImageView share;
    private Movie movie;


    public static DetailFragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putSerializable(MOVIE, movie);

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_detail, container, false);

        imagemPost = view.findViewById(R.id.imagem_act_id);
        share = view.findViewById(R.id.image_compartilhar);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShareClicado(movie);
            }
        });

        movie = (Movie) getArguments().getSerializable(MOVIE);

        String titulo = movie.getNome();
        TextView tituloText = view.findViewById(R.id.titulo_act_id);
        tituloText.setText(titulo);


        String descricao = movie.getSinopse();
        TextView descricaoText = view.findViewById(R.id.sinopse_act_id);
        descricaoText.setText(descricao);


        String poster = movie.getPoster();
        Picasso.get().load("http://image.tmdb.org/t/p/w500/" + poster).into(imagemPost);


        return view;

    }

    public void onShareClicado(Movie movie){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT, movie.getNome());
        share.putExtra(Intent.EXTRA_TEXT, movie.getSinopse());
        startActivity(Intent.createChooser(share, movie.getPoster()));
    }


}
