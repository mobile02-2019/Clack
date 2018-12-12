package br.com.digitalhouse.clackapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.digitalhouse.clackapp.model.Preference;

public class LoginActivity extends Activity {

    private TextView textViewHelloLogin;
    private TextView textViewEntreLogin;
    private static final String TAG = "login";
    private static final int RC_SIGN_IN = 1000;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    public static final String CHAVE_EMAIL = "chave_email";
    private CallbackManager callbackManager;
    private LoginButton loginFacebook;
    private ArrayList<String> listaChecados = new ArrayList<>();
    private ProgressBar progressBar;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final AutoCompleteTextView emailDigitado = findViewById(R.id.login_email_id);
        final EditText passwordDigitado = findViewById(R.id.login_password_id);
        final int colorDefaultEmail = emailDigitado.getCurrentTextColor();
        final int colorDefaultPassword = passwordDigitado.getCurrentTextColor();

        /*Typeface myCustomFontLogo = Typeface.createFromAsset(getAssets(), "fonts/LuckiestGuy-Regular.ttf");
        textViewHelloLogin.setTypeface(myCustomFontLogo);


        textViewEntreLogin.setTypeface(myCustomFontLogo);*/

//        sing com google: - inicio do codigo
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.login_progress);

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        ImageView btnGoogleSignIn = findViewById(R.id.google_sign_in_id);
        btnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.google_sign_in_id:
                        signIn();
                        break;
                }
            }
        });

        loginFacebook = findViewById(R.id.button_facebookLogin_id);
        //Configuração para modificar botão original do facebook
        loginFacebook.setBackgroundResource(R.drawable.icon_facebook_circled_preto_96px);
        loginFacebook.setScaleY(2.25F);
        loginFacebook.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        loginFacebook.setReadPermissions("email", "public_profile");
        callbackManager = CallbackManager.Factory.create();
        loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });
        //TODO registro
        TextView register = findViewById(R.id.register_now_id);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        //TODO LOGIN COM EMAIL E SENHA
        Button loginClicado = findViewById(R.id.login_button);
        loginClicado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(v.getContext(), PreferenceActivity.class);
                final Bundle bundle = new Bundle();

                final Button buttonLogin = findViewById(R.id.login_button);


                if (!emailDigitado.getText().toString().equals("") && !passwordDigitado.getText().toString().equals("")) {
                    mAuth.signInWithEmailAndPassword(emailDigitado.getText().toString(), passwordDigitado.getText().toString())
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        bundle.putString(CHAVE_EMAIL, emailDigitado.getText().toString());
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    } else {
                                        emailDigitado.setTextColor(getResources().getColor(R.color.colorBlack));
                                        passwordDigitado.setTextColor(getResources().getColor(R.color.colorBlack));

                                        Snackbar.make(buttonLogin, "Invalid email and/or password.", Snackbar.LENGTH_INDEFINITE)
                                                .setAction("Got it.", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        emailDigitado.setTextColor(colorDefaultEmail);
                                                        passwordDigitado.setTextColor(colorDefaultPassword);
                                                    }
                                                }).show();
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    }
                                }
                            });
                } else {
                    Toast.makeText(LoginActivity.this, "You need to provide an email and a password in order to Log In.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

     @Override
     public void onStart() {
       super.onStart();

        if(mAuth.getCurrentUser()!=null){

            Toast.makeText(this, "Autenticando usuário, aguarde...", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.VISIBLE);

        FirebaseUser user = mAuth.getCurrentUser();
            try {
                //referencia database firebase
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("preferences/" + mAuth.getUid());
                //tenta buscar preferencia
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //tenta atribuir preferencia do firebase
                        Preference preference = dataSnapshot.getValue(Preference.class);
                        Log.d(TAG, "Value is: " + preference);

                        //se existir preferencias, criar array de string com os generos e enviar para Home
                        if (preference != null) {
                            //abre um intent
                            //Cria bundle
                            Bundle bundleParaHome = new Bundle();
                            //adiciona na lista string de generos
                            listaChecados.add(preference.getPreferenciaSelecionada1());
                            listaChecados.add(preference.getPreferenciaSelecionada2());
                            listaChecados.add(preference.getPreferenciaSelecionada3());
                            listaChecados.add(preference.getPreferenciaSelecionada4());
                            //adiciona lista de string no bundle
                            bundleParaHome.putStringArrayList("checados" , listaChecados);
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            //adiciona bundle no intent
                            intent.putExtras(bundleParaHome);
                            startActivity(intent);
                        }else{//se nao existir preferencia, vai para tela de preferencias para serem criadas
                            //abre a outra Activity
                            Intent intent = new Intent(getApplicationContext(),PreferenceActivity.class);
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            } catch (Exception ex) {

            }

        }
}

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            goToMain(user);
//                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "A autenticacao falhou, tente novamente!", Toast.LENGTH_LONG).show();
//                            Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void goToMain(FirebaseUser user) {
        irParaPreferencesGenerico(user);
    }

    private void signIn() {
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);

    }

    public void registerNow(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            firebaseAuthWithGoogle(account);

            // Signed in successfully, show authenticated UI.
//            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            goToMain(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }

//sign in com o google. fim do codigo do google.

    public void irParaPreferencesGenerico(FirebaseUser user) {
        Intent intent = new Intent(this, PreferenceActivity.class);
        startActivity(intent);
    }

}