package br.com.digitalhouse.clackapp.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.digitalhouse.clackapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    public static final String TITULO = "titulo";
    public static final String DESCRICAO = "descricao";
    public static final String POSTER = "poster";
    private ImageView imagemUser;

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

        imagemUser = view.findViewById(R.id.imagem_act_id);

        Bundle bundle1 = getArguments();
        String titulo = bundle1.getString(TITULO);
        TextView tituloText = view.findViewById(R.id.titulo_act_id);
        Typeface myCustomFontLogo = Typeface.createFromAsset(getAssets(), "fonts/LuckiestGuy-Regular.ttf");
        tituloText.setTypeface(myCustomFontLogo);
        tituloText.setText(titulo);

        Bundle bundle2 = getArguments();
        String descricao = bundle2.getString(DESCRICAO);
        TextView descricaoText = view.findViewById(R.id.sinopse_act_id);
        descricaoText.setText(descricao);

        Bundle bundle3 = getArguments();
        String poster = bundle3.getString(POSTER);
        Picasso.get().load("http://image.tmdb.org/t/p/w500/" + poster).into(imagemUser);


        return view;



    }

}
