package br.com.digitalhouse.clackapp;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.clackapp.interfaces.RealtimeDatabasePreferenciaCall;
import br.com.digitalhouse.clackapp.model.User;
import br.com.digitalhouse.clackapp.model.dto.PreferenceDTO;

public class PreferenceActivity extends AppCompatActivity implements RealtimeDatabasePreferenciaCall {

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
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef;

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
                        // TODO Write a message to the database
                        inserindoDadosUser();
                        /*myRef = database.getReference("preferences/" + mAuth.getCurrentUser().getUid());
                        myRef.setValue(checkBoxListChecked);*/
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

        if (user != null) {
            Picasso.get().load(user.getPhotoUrl()).into(imageViewProfile);
            textViewHelloPref.setText("Olá  " + user.getDisplayName() + "!");
        }


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

    //TODO nesse método que seria setado o checkbox
    private void inserindoDadosUser (){
        User user = new User();
        String user_id = mAuth.getCurrentUser().getUid();
        DatabaseReference current_user_db = myRef.child(user_id);

        Boolean box1Checked = checkBoxAcao.isChecked();
        myRef.setValue(box1Checked);

        if(checkBoxAcao.isChecked()) {
            current_user_db.child("1").setValue("Check Action");
        }

        if(checkBoxAnimacao.isChecked()) {
            current_user_db.child("2").setValue("Check Animation");
        }

        if(checkBoxAventura.isChecked()) {
            current_user_db.child("3").setValue("Check Adventure");
        }

        if(checkBoxCinemaTv.isChecked()) {
            current_user_db.child("4").setValue("Check CineTV");
        }

        if(checkBoxComedia.isChecked()) {
            current_user_db.child("5").setValue("Check Comedy");
        }

        if(checkBoxCrime.isChecked()) {
            current_user_db.child("6").setValue("Check Crime");
        }

        if(checkBoxDocumentario.isChecked()) {
            current_user_db.child("7").setValue("Check Documentary");
        }

        if(checkBoxDrama.isChecked()) {
            current_user_db.child("8").setValue("Check Drama");
        }

        if(checkBoxFamilia.isChecked()) {
            current_user_db.child("9").setValue("Check Family");
        }

        if(checkBoxFantasia.isChecked()) {
            current_user_db.child("10").setValue("Check Fantasy");
        }

        if(checkBoxFaroeste.isChecked()) {
            current_user_db.child("11").setValue("Check Far west");
        }

        if(checkBoxFiccaoCientifica.isChecked()) {
            current_user_db.child("12").setValue("Check Science Fiction");
        }

        if(checkBoxGuerra.isChecked()) {
            current_user_db.child("13").setValue("Check War");
        }

        if(checkBoxHistoria.isChecked()) {
            current_user_db.child("14").setValue("Check History");
        }

        if(checkBoxMisterio.isChecked()) {
            current_user_db.child("15").setValue("Check Mistery");
        }

        if(checkBoxMusica.isChecked()) {
            current_user_db.child("16").setValue("Check Music");
        }

        if(checkBoxRomance.isChecked()) {
            current_user_db.child("17").setValue("Check Romance");
        }

        if(checkBoxTerror.isChecked()) {
            current_user_db.child("18").setValue("Check Terror");
        }

        if(checkBoxThriller.isChecked()) {
            current_user_db.child("19").setValue("Check Thriller");
        }

        Intent interestIntent = new Intent(this, MainActivity.class);
        interestIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(interestIntent);

    }

    @Override
    public void onDataChange(PreferenceDTO preferenceDTO) {

    }

    @Override
    public void onDataCanceled() {

    }
}