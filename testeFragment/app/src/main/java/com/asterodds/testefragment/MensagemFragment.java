package com.asterodds.testefragment;


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
public class MensagemFragment extends Fragment {

    receptorInterface receptorInterface;
    EditText mensagemRecebida;

    public interface receptorInterface {
        void ReceberMensagem(String mensagem);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        receptorInterface = (receptorInterface) context;

    }

    public MensagemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mensagem, container, false);

        mensagemRecebida = view.findViewById(R.id.mensagem_edit);

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
        receptorInterface.ReceberMensagem(mensagem);

    }


}
