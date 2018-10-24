package com.asterodds.aulareforco;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReceberFragment extends Fragment {


    public ReceberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_receber, container, false);
        ((TextView)view.findViewById(R.id.receber_id)).setText(getArguments().getString(MainActivity.MENSAGEM));

        return view;
    }

}
