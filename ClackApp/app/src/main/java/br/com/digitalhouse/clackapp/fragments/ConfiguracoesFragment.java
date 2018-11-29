package br.com.digitalhouse.clackapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
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
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import br.com.digitalhouse.clackapp.LoginActivity;
import br.com.digitalhouse.clackapp.PreferenceActivity;
import br.com.digitalhouse.clackapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfiguracoesFragment extends Fragment {

    private ConstraintLayout constraintFavorito, constraintHome;
    private TextView textTituloConf;
    private Switch switchNot, switchTraducao, switchLuz;
    private Button btnSalvar, btnLogout, btnEditPreferencias;
    private TextView textView1id;
    private FacebookButtonBase facebookButtonBase;

    public ConfiguracoesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_configuracoes, container, false);


//        // create ContextThemeWrapper from the original Activity Context with the custom theme
//        final Context contextThemeWrapper = new ContextThemeWrapper(getContext(), R.style.AppEscuro);
//
//        // clone the inflater using the ContextThemeWrapper
//        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

//        // inflate the layout using the cloned inflater, not default inflater
//        return localInflater.inflate(R.layout.activity_main, container, false);

        // iniciar um interruptor
//        Switch luzSwitch = view.findViewById (R.id.switcher_luz);

        switchNot = view.findViewById(R.id.switcher_notificacao);

        switchTraducao = view.findViewById(R.id.switcher_traducao);

//        switchLuz = view.findViewById(R.id.switcher_luz);

        textTituloConf = view.findViewById(R.id.textTituloConf);

        constraintFavorito = view.findViewById(R.id.constraint_fav);

//        btnSalvar = view.findViewById(R.id.btn_salvar);

        btnLogout = view.findViewById(R.id.button_logout_id);

        btnEditPreferencias = view.findViewById(R.id.btn_edit_preferencias);

        textView1id = view.findViewById(R.id.textView_1_id);

        constraintHome = view.findViewById(R.id.constraint_home);

//        switchLuz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                // do something, the isChecked will be
//                // true if the switch is in the On position
//                if (isChecked == true ){
//                    // transformar em inteiro
//                    int clackColorValue = Color.parseColor("#ffe81a");
//                    constraintFavorito.setBackgroundColor(clackColorValue);
//                    textTituloConf.setTextColor(getResources().getColor(android.R.color.black));
//                    textTituloConf.setTextColor(getResources().getColor(android.R.color.black));
//                    switchNot.setTextColor(getResources().getColor(android.R.color.black));
//                    switchTraducao.setTextColor(getResources().getColor(android.R.color.black));
//                    switchLuz.setTextColor(getResources().getColor(android.R.color.black));
//                    btnSalvar.setTextColor(getResources().getColor(android.R.color.white));
//                    btnSalvar.setBackgroundColor(getResources().getColor(android.R.color.black));
////                    textView1id.setBackgroundColor(getResources().getColor(android.R.color.black));
////                    constraintHome.setBackgroundColor(clackColorValue);
//
//                }
//                if(isChecked == false) {
////                    contextThemeWrapper.setTheme(R.style.AppEscuro);
//                    int clackColorValue = Color.parseColor("#ffe81a");
//
//                    constraintFavorito.setBackgroundColor(getResources().getColor(android.R.color.black));
//                    textTituloConf.setTextColor(getResources().getColor(android.R.color.white));
//                    switchNot.setTextColor(getResources().getColor(android.R.color.white));
//                    switchTraducao.setTextColor(getResources().getColor(android.R.color.white));
//                    switchLuz.setTextColor(getResources().getColor(android.R.color.white));
//                    btnSalvar.setTextColor(getResources().getColor(android.R.color.black));
//                    btnSalvar.setBackgroundColor(clackColorValue);
//
//
////                    switchNot.setTextColor(getResources().getColor(android.R.color.white));
////                    switchTraducao.setTextColor(getResources().getColor(android.R.color.white));
////                    switchLuz.setTextColor(getResources().getColor(android.R.color.white));
//                }
//            }
//        });

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

    private void deslogarDoAplicativo() {
        FirebaseAuth.getInstance().signOut();
    }


}
