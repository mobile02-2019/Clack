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
    public static final String TITULO = "titulo";
    public static final String DESCRICAO = "descricao";
    public static final String POSTER = "poster";
    private ImageView imagemPost;
    private ImageView share;
    private Movie movie;

    public static DetailFragment newInstance(String titulo, String descricao, String poster) {
        Bundle args = new Bundle();
        args.putString(TITULO, titulo);
        args.putString(DESCRICAO, descricao);
        args.putString(POSTER, poster);

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



        Bundle bundle1 = getArguments();
        String titulo = bundle1.getString(TITULO);
        TextView tituloText = view.findViewById(R.id.titulo_act_id);
        tituloText.setText(titulo);

        Bundle bundle2 = getArguments();
        String descricao = bundle2.getString(DESCRICAO);
        TextView descricaoText = view.findViewById(R.id.sinopse_act_id);
        descricaoText.setText(descricao);

        Bundle bundle3 = getArguments();
        String poster = bundle3.getString(POSTER);
        Picasso.get().load("http://image.tmdb.org/t/p/w500/" + poster).into(imagemPost);


        return view;

    }

    public void onShareClicado(Movie movie){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT, movie.getNome());
        share.putExtra(Intent.EXTRA_TEXT, movie.getSinopse());
        startActivity(Intent.createChooser(share, (CharSequence) movie.getData()));
    }


}
