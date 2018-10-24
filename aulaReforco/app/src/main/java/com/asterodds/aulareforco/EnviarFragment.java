package com.asterodds.aulareforco;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnviarFragment extends Fragment {

    enviarMensagem enviarMensagem;
    EditText mensagemRecebida;


    public interface enviarMensagem{
        void ReceberMensagem(String mensagem);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        enviarMensagem = (EnviarFragment.enviarMensagem) context;
    }

    public EnviarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_enviar, container, false);

        mensagemRecebida = view.findViewById(R.id.enviar_id);

        view.findViewById(R.id.bt_enviar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensagem();
            }
        });


        return view;
    }

    private void enviarMensagem() {
        String mensagem = mensagemRecebida.getText().toString();
        enviarMensagem.ReceberMensagem(mensagem);

    }

}
