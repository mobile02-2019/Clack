package br.com.digitalhouse.clackapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    //Definir Constante de Tempo 5 segundos
    private final static int TIME_SPLASH = 2000;

    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        t=(TextView) findViewById(R.id.logo_id);
        Typeface myCustomFontLogo = Typeface.createFromAsset(getAssets(), "fonts/LuckiestGuy-Regular.ttf");
        t.setTypeface(myCustomFontLogo);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                irParaLogin();
            }
        }, TIME_SPLASH);

    }

    public void irParaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}