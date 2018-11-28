package br.com.digitalhouse.clackapp.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.Toast;

import com.bumptech.glide.util.Util;

import br.com.digitalhouse.clackapp.MainActivity;
import br.com.digitalhouse.clackapp.R;
import br.com.digitalhouse.clackapp.interfaces.MandarSwitchListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfiguracoesFragment extends Fragment {
    ConstraintLayout constraintFavorito, constraintHome;
    TextView textTituloConf;
    Switch switchNot, switchTraducao, switchLuz;
    TextView btnSalvar;
    TextView textView1id;
    private MandarSwitchListener mandarSwitchListener;
    Button themeLight;
    Button themeDark;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mandarSwitchListener = (MandarSwitchListener) context;
    }

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

        switchLuz = view.findViewById(R.id.switcher_luz);

        textTituloConf = view.findViewById(R.id.textTituloConf);

        constraintFavorito = view.findViewById(R.id.constraint_fav);

        btnSalvar = view.findViewById(R.id.btn_salvar);

        textView1id = view.findViewById(R.id.textView_1_id);

        constraintHome = view.findViewById(R.id.constraint_home);

        themeLight = view.findViewById(R.id.botao_theme_light);

        themeDark = view.findViewById(R.id.botao_theme_dark);


        SharedPreferences sharedPreferences = getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("teste", switchLuz.isChecked());
        editor.apply();

        themeLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trocarCores(false);

            }
        });
        themeDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trocarCores(true);
            }
        });




        switchLuz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



         if (isChecked == true ){


                   int clackColorValue = Color.parseColor("#ffe81a");
                    constraintFavorito.setBackgroundColor(clackColorValue);
                    textTituloConf.setTextColor(getResources().getColor(android.R.color.black));
                    textTituloConf.setTextColor(getResources().getColor(android.R.color.black));
                    switchNot.setTextColor(getResources().getColor(android.R.color.black));
                    switchTraducao.setTextColor(getResources().getColor(android.R.color.black));
                    switchLuz.setTextColor(getResources().getColor(android.R.color.black));
                    btnSalvar.setTextColor(getResources().getColor(android.R.color.white));
                    btnSalvar.setBackgroundColor(getResources().getColor(android.R.color.black));




             }
            if(isChecked == false) {

                int clackColorValue = Color.parseColor("#ffe81a");

                constraintFavorito.setBackgroundColor(getResources().getColor(android.R.color.black));
                textTituloConf.setTextColor(getResources().getColor(android.R.color.white));
                switchNot.setTextColor(getResources().getColor(android.R.color.white));
                switchTraducao.setTextColor(getResources().getColor(android.R.color.white));
                switchLuz.setTextColor(getResources().getColor(android.R.color.white));
                btnSalvar.setTextColor(getResources().getColor(android.R.color.black));
                btnSalvar.setBackgroundColor(clackColorValue);

            }
        }




    });

        return view;}

    private void trocarCores(boolean info) {

        mandarSwitchListener.enviarInformacao(info);
    }


}





