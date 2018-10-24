package com.asterodds.testefragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReceptorFragment extends Fragment {


    public ReceptorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_receptor, container, false);
        ((TextView)view.findViewById(R.id.mensagem_recebida)).setText(getArguments().getString(MainActivity.CHAVE_MENSAGEM));

        return view;
    }

}
