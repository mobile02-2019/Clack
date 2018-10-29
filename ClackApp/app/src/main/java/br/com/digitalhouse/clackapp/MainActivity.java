package br.com.digitalhouse.clackapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.digitalhouse.clackapp.fragment.FavoritosFragment;
import br.com.digitalhouse.clackapp.fragments.ConfiguracoesFragment;
import br.com.digitalhouse.clackapp.fragments.PesquisaFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        navigationView.setOnNavigationItemSelectedListener(this);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.navigation_home: {
                Toast.makeText(this, "Você já está nessa tela.", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.navigation_favoritos: {
                replaceFragment(new FavoritosFragment());
                break;
            }
            case R.id.navigation_pesquisa: {
                replaceFragment(new PesquisaFragment());
                break;
            }
            case R.id.navigation_config: {
                replaceFragment(new ConfiguracoesFragment());
                break;
            }

        }

        return true;
    }

    public void replaceFragment (Fragment fragment){
       FragmentManager manager = getSupportFragmentManager();
       FragmentTransaction transaction = manager.beginTransaction();
       transaction.replace(R.id.container_main_id,fragment);
       transaction.commit();
    }



}
