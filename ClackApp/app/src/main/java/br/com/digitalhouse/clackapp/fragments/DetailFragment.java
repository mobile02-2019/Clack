package br.com.digitalhouse.clackapp.fragments;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.digitalhouse.clackapp.R;
import br.com.digitalhouse.clackapp.database.FilmesFavoritosContract;
import br.com.digitalhouse.clackapp.database.FilmesFavoritosDbHelper;
import br.com.digitalhouse.clackapp.model.Movie;
import br.com.digitalhouse.clackapp.service.RetrofitService;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    public static final String MOVIE = "MOVIE";
    private ImageView imagemPost;
    private ImageView share;
    private Movie movie;
    private Button botaoSalva;
    private ImageButton botaoFechar;

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
        View view = inflater.inflate(R.layout.fragment_detail, container, false);


        imagemPost = view.findViewById(R.id.imagem_act_id);
        share = view.findViewById(R.id.image_compartilhar);
        botaoFechar = view.findViewById(R.id.botao_fechar_id);


        botaoFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });


//        botaoSalva = view.findViewById(R.id.botao_para_salva);
//        botaoSalva.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                salvarFilmeLocal(movie);
//
//
//            }
//        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShareClicado(movie);
            }
        });

        movie = (Movie) getArguments().getSerializable(MOVIE);

        String titulo = movie.getNome();
        TextView tituloText = view.findViewById(R.id.titulo_act_id);
        Typeface myCustomFontLogo = Typeface.createFromAsset(getContext().getAssets(), "fonts/LuckiestGuy-Regular.ttf");
        tituloText.setTypeface(myCustomFontLogo);
        tituloText.setText(titulo);


        String descricao = movie.getSinopse();
        TextView descricaoText = view.findViewById(R.id.sinopse_act_id);
        descricaoText.setText(descricao);




        String poster = movie.getPoster();
        Picasso.get().load(RetrofitService.BASE_IMAGE_URL + poster).into(imagemPost);

        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //
        //String dateString = format.format( new Date()   );
        //Date   date       = format.parse ( "2009-12-31" );

        //Date date = new Date();
        //    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        //    String strDate= formatter.format(date);

        Date data = movie.getData();
        TextView dataText = view.findViewById(R.id.textView_data_id);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formatter.format(data);
        dataText.setText("Data de lançamento: " + dataFormatada);

        float nota = movie.getNota();
        TextView notaText = view.findViewById(R.id.textView_nota_id);
        notaText.setText("Nota média: " + nota);

        return view;

    }

    private void salvarFilmeLocal(Movie movie) {
        // Gets the data repository in write mode
        FilmesFavoritosDbHelper mDbHelper = new FilmesFavoritosDbHelper(getContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_NAME_TITLE, movie.getNome());
        values.put(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_DATE, movie.getData().toString());
        values.put(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_POSTER, movie.getPoster());
        values.put(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_NOTA, movie.getNota());
        values.put(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_GENERO, movie.getGeneros().toString());
        values.put(FilmesFavoritosContract.FilmesFavoritosEntry.COLUMN_SINOPSE, movie.getGeneros().toString());



// Insert the new row, returning the primary key value of the new row
       long newRowId = db.insert(FilmesFavoritosContract.FilmesFavoritosEntry.TABLE_NAME, null, values);

    }



    public void onShareClicado(Movie movie){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT, movie.getNome());
        share.putExtra(Intent.EXTRA_TEXT, "https://image.tmdb.org/t/p/w500" + movie.getPoster());
        startActivity(Intent.createChooser(share, movie.getPoster()));
    }


}
