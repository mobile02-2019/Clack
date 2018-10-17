package br.com.digitalhouse.clackapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void filmeEscolhido(View view) {

        Intent intent = new Intent(this,DetailActivity.class);
        startActivity(intent);

    }
}
