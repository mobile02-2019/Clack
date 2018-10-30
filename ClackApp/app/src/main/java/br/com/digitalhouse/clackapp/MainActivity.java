package br.com.digitalhouse.clackapp;

import android.app.Activity;
import android.content.Intent;
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
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.clackapp.adapter.FragmentViewPagerAdapter;
import br.com.digitalhouse.clackapp.fragment.FavoritosFragment;
import br.com.digitalhouse.clackapp.fragments.ConfiguracoesFragment;
import br.com.digitalhouse.clackapp.fragments.HomeFragment;
import br.com.digitalhouse.clackapp.fragments.PesquisaFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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



    public void replaceFragment (Fragment fragment){
       FragmentManager manager = getSupportFragmentManager();
       FragmentTransaction transaction = manager.beginTransaction();
       transaction.replace(R.id.container_main_id,fragment);
       transaction.commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.navigation_home: {
                replaceFragment(new HomeFragment());
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


}
