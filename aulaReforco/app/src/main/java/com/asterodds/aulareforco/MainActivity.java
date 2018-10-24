package com.asterodds.aulareforco;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements EnviarFragment.enviarMensagem {

    public static final String MENSAGEM = "mensagem";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.receber_id, new EnviarFragment());
        transaction.commit();

    }

    @Override
    public void ReceberMensagem(String mensagem) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString(MENSAGEM, mensagem);

        ReceberFragment receberFragment = new ReceberFragment();
        receberFragment.setArguments(bundle);

        transaction.replace(R.id.mostrar_mensagem_id, new EnviarFragment());
        transaction.commit();

    }
}


