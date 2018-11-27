package br.com.digitalhouse.clackapp;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import br.com.digitalhouse.clackapp.adapter.FragmentViewPagerAdapter;
import br.com.digitalhouse.clackapp.fragments.DetailFragment;
import br.com.digitalhouse.clackapp.fragments.FavoritosFragment;
import br.com.digitalhouse.clackapp.fragments.ConfiguracoesFragment;
import br.com.digitalhouse.clackapp.fragments.HomeFragment;
import br.com.digitalhouse.clackapp.fragments.PesquisaFragment;
import br.com.digitalhouse.clackapp.interfaces.CardMovieClicado;
import br.com.digitalhouse.clackapp.interfaces.MandarSwitchListener;
import br.com.digitalhouse.clackapp.interfaces.ReceptorMovie;
import br.com.digitalhouse.clackapp.model.Movie;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity implements ReceptorMovie,MandarSwitchListener {

    public static final String THEME_PREFERENCE = "THEME_PREFERENCE";
    private BottomNavigationView navigationView;
    MenuItem prevMenuItem;
    private DetailFragment fragmentDetalhe;
    private boolean respostaSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //todo coisa pra fazer aqui
        SharedPreferences preferencess = this.getSharedPreferences(
                "br.com.digitalhouse.clackapp", Context.MODE_PRIVATE);
        boolean lightTheme = preferencess.getBoolean(THEME_PREFERENCE, false);

        setTheme(lightTheme ? R.style.AppTheme : R.style.AppThemeDark);
        Toast.makeText(this,"o tema é " + lightTheme,Toast.LENGTH_LONG).show();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ViewPager viewPager = findViewById(R.id.viewpager_id);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new FavoritosFragment());
        fragmentList.add(new PesquisaFragment());
        fragmentList.add(new ConfiguracoesFragment());

        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(),fragmentList);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        navigationView = (BottomNavigationView) findViewById(R.id.navigationView);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                for (Fragment fragment:getSupportFragmentManager().getFragments()) {
                    getSupportFragmentManager().beginTransaction().commit();
                }
                switch (menuItem.getItemId()){
                    case R.id.navigation_home: {
                        viewPager.setCurrentItem(0);
                        break;
                    }
                    case R.id.navigation_favoritos: {
                        viewPager.setCurrentItem(1);
                        break;
                    }
                    case R.id.navigation_pesquisa: {
                        viewPager.setCurrentItem(2);
                        break;
                    }
                    case R.id.navigation_config: {
                        viewPager.setCurrentItem(3);
                        break;
                    }

                }

                return true;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    navigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                navigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navigationView.getMenu().getItem(position);

            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }



    public void replaceFragment(Fragment fragment){
       FragmentManager manager = getSupportFragmentManager();
       FragmentTransaction transaction = manager.beginTransaction();
       transaction.replace(R.id.container_main_id,fragment);
       transaction.addToBackStack(null);
       transaction.commit();
    }



    @Override
    public void receberMovieClicado(Movie movie) {
        Fragment detailFrag = DetailFragment.newInstance(movie);
        replaceFragment(detailFrag);
    }


    @Override
    public void enviarInformacao(boolean isChecked) {
        // TODO gravar nas preferencias
      //TODO AQUIIIIIII

        SharedPreferences prefs = this.getSharedPreferences(
                "br.com.digitalhouse.clackapp", Context.MODE_PRIVATE);
        prefs.edit().putBoolean(THEME_PREFERENCE,isChecked).apply();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

        //recreate();




    }
}
