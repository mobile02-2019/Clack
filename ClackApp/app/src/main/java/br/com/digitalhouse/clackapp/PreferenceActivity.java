package br.com.digitalhouse.clackapp;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PreferenceActivity extends AppCompatActivity {

    private TextView textViewHelloPref;
    private ImageView imageViewProfile;
    private CheckBox checkBoxAcao;
    private CheckBox checkBoxAnimacao;
    private CheckBox checkBoxAventura;
    private CheckBox checkBoxCinemaTv;
    private CheckBox checkBoxComedia;
    private CheckBox checkBoxCrime;
    private CheckBox checkBoxDocumentario;
    private CheckBox checkBoxDrama;
    private CheckBox checkBoxFamilia;
    private CheckBox checkBoxFantasia;
    private CheckBox checkBoxFaroeste;
    private CheckBox checkBoxFiccaoCientifica;
    private CheckBox checkBoxGuerra;
    private CheckBox checkBoxHistoria;
    private CheckBox checkBoxMisterio;
    private CheckBox checkBoxMusica;
    private CheckBox checkBoxRomance;
    private CheckBox checkBoxTerror;
    private CheckBox checkBoxThriller;
    private FloatingActionButton floatingActionButton;
    private List<CheckBox> checkBoxListAll = new ArrayList<>();
    private ArrayList<String> checkBoxListChecked = new ArrayList<>();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        setupIds();
        getCheckBoxListAll();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBoxListChecked.clear();
                for (CheckBox checkBox : checkBoxListAll) {
                    if (checkBox.isChecked()) {
                        checkBoxListChecked.add(checkBox.getText().toString());
                    }
                }
                if (checkBoxListChecked.size() == 4) {
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    intent.putExtras(bundleHome());
                    startActivity(intent);
                } else {
                    Toast.makeText(PreferenceActivity.this, "Selecione 4 gêneros preferidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        Picasso.get().load(user.getPhotoUrl()).into(imageViewProfile);
        textViewHelloPref.setText("Olá  " + user.getDisplayName() + "!");
    }

    public void setupIds() {
        textViewHelloPref = findViewById(R.id.textView_hello_pref_id);
        Typeface myCustomFontLogo = Typeface.createFromAsset(getAssets(), "fonts/LuckiestGuy-Regular.ttf");
        textViewHelloPref.setTypeface(myCustomFontLogo);
        imageViewProfile = findViewById(R.id.imageView_profile_id);
        checkBoxAcao = findViewById(R.id.checkbox_acao_id);
        checkBoxAnimacao = findViewById(R.id.checkbox_animacao_id);
        checkBoxAventura = findViewById(R.id.checkbox_aventura_id);
        checkBoxCinemaTv = findViewById(R.id.checkbox_cinematv_id);
        checkBoxComedia = findViewById(R.id.checkbox_comedia_id);
        checkBoxCrime = findViewById(R.id.checkbox_crime_id);
        checkBoxDocumentario = findViewById(R.id.checkbox_documentario_id);
        checkBoxDrama = findViewById(R.id.checkbox_drama_id);
        checkBoxFamilia = findViewById(R.id.checkbox_familia_id);
        checkBoxFantasia = findViewById(R.id.checkbox_fantasia_id);
        checkBoxFaroeste = findViewById(R.id.checkbox_faroeste_id);
        checkBoxFiccaoCientifica = findViewById(R.id.checkbox_ficcaocientifica_id);
        checkBoxGuerra = findViewById(R.id.checkbox_guerra_id);
        checkBoxHistoria = findViewById(R.id.checkbox_historia_id);
        checkBoxMisterio = findViewById(R.id.checkbox_misterio_id);
        checkBoxMusica = findViewById(R.id.checkbox_musica_id);
        checkBoxRomance = findViewById(R.id.checkbox_romance_id);
        checkBoxTerror = findViewById(R.id.checkbox_terror_id);
        checkBoxThriller = findViewById(R.id.checkbox_thriller_id);
        floatingActionButton = findViewById(R.id.fab_save_id);
    }

    public List<CheckBox> getCheckBoxListAll() {
        checkBoxListAll.add(checkBoxAcao);
        checkBoxListAll.add(checkBoxAnimacao);
        checkBoxListAll.add(checkBoxAventura);
        checkBoxListAll.add(checkBoxCinemaTv);
        checkBoxListAll.add(checkBoxComedia);
        checkBoxListAll.add(checkBoxCrime);
        checkBoxListAll.add(checkBoxDocumentario);
        checkBoxListAll.add(checkBoxDrama);
        checkBoxListAll.add(checkBoxFamilia);
        checkBoxListAll.add(checkBoxFantasia);
        checkBoxListAll.add(checkBoxFaroeste);
        checkBoxListAll.add(checkBoxFiccaoCientifica);
        checkBoxListAll.add(checkBoxGuerra);
        checkBoxListAll.add(checkBoxHistoria);
        checkBoxListAll.add(checkBoxMisterio);
        checkBoxListAll.add(checkBoxMusica);
        checkBoxListAll.add(checkBoxRomance);
        checkBoxListAll.add(checkBoxTerror);
        checkBoxListAll.add(checkBoxThriller);
        return checkBoxListAll;
    }

    public Bundle bundleHome() {
        Bundle bundleHome = new Bundle();
        bundleHome.putStringArrayList("checados" ,checkBoxListChecked);
        return bundleHome;
    }

}