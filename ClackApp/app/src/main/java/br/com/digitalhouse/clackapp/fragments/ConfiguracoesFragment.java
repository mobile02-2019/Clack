package br.com.digitalhouse.clackapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import br.com.digitalhouse.clackapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfiguracoesFragment extends Fragment {


    public ConfiguracoesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_configuracoes, container, false);


        return view;


    }

}
