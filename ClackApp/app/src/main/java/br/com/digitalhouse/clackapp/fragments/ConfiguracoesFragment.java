package br.com.digitalhouse.clackapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.util.Util;
import com.facebook.FacebookButtonBase;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.digitalhouse.clackapp.LoginActivity;
import br.com.digitalhouse.clackapp.PreferenceActivity;
import br.com.digitalhouse.clackapp.R;
import br.com.digitalhouse.clackapp.adapter.RecyclerViewMovieAdapter;
import br.com.digitalhouse.clackapp.interfaces.ReceptorMovie;
import br.com.digitalhouse.clackapp.interfaces.ServiceListener;
import br.com.digitalhouse.clackapp.model.Movie;
import br.com.digitalhouse.clackapp.model.dao.MovieDAO;
import de.hdodenhof.circleimageview.CircleImageView;

import static br.com.digitalhouse.clackapp.fragments.DetailFragment.MOVIE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfiguracoesFragment extends Fragment implements ServiceListener {


    private Button btnLogout, btnEditPreferencias,btnEstouComSorte;
    private DetailFragment detailFragment;
    private Movie movie;
    private RecyclerViewMovieAdapter movieAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    public ConfiguracoesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_configuracoes, container, false);


        btnLogout = view.findViewById(R.id.button_logout_id);
        btnEditPreferencias = view.findViewById(R.id.btn_edit_preferencias);
        btnEstouComSorte = view.findViewById(R.id.btn_estou_com_sorte_id);

        btnEstouComSorte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                buscarFilme();



    //            movie = movieAdapter.getItemCount();


            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deslogarDoAplicativo();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnEditPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PreferenceActivity.class);
                startActivity(intent);
            }
        });


        return view;


    }

    private void buscarFilme() {
        Random random = new Random();
        MovieDAO dao = new MovieDAO();
        dao.getMovieById(this,random.nextInt(124));
    }

    private void deslogarDoAplicativo() {
        FirebaseAuth.getInstance().signOut();
        //TODO com essa frase abaixo desloga corretamente do face
        LoginManager.getInstance().logOut();
    }


    @Override
    public void onSuccess(Object object, Integer adapter) {

    }

    @Override
    public void onSuccess(Object object) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(MOVIE, (Movie)object);
        detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);


        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.addToBackStack(null);
        transaction.replace(R.id.container_detalhes_id, detailFragment);
        transaction.commit();
    }

    @Override
    public void onError(Throwable throwable) {

    }
}
