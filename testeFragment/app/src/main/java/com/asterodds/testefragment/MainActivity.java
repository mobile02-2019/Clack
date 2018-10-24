package com.asterodds.testefragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MensagemFragment.receptorInterface {

    public static final String CHAVE_MENSAGEM = "chave_mensagem";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frag_mensagem_id, new MensagemFragment());
        transaction.commit();

    }


    @Override
    public void ReceberMensagem(String mensagem) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString(CHAVE_MENSAGEM,mensagem);

        ReceptorFragment receptorFragment = new ReceptorFragment();
        receptorFragment.setArguments(bundle);

        transaction.replace(R.id.frag_receptor_id,new ReceptorFragment());
        transaction.commit();

    }

}
