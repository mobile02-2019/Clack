package br.com.digitalhouse.clackapp;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
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

public class LoginActivity extends Activity {

    private TextView textViewHelloLogin;
    private TextView textViewEntreLogin;
    private static final String TAG= "login" ;
    private static final int RC_SIGN_IN = 1000 ;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    public static final String CHAVE_EMAIL = "chave_email";
    private CallbackManager callbackManager;
    private LoginButton loginFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final AutoCompleteTextView emailDigitado = findViewById(R.id.login_email_id);
        final EditText passwordDigitado = findViewById(R.id.login_password_id);
        final int colorDefaultEmail = emailDigitado.getCurrentTextColor();
        final int colorDefaultPassword = passwordDigitado.getCurrentTextColor();

        textViewHelloLogin = findViewById(R.id.textView_hello_login_id);
        Typeface myCustomFontLogo = Typeface.createFromAsset(getAssets(), "fonts/LuckiestGuy-Regular.ttf");
        textViewHelloLogin.setTypeface(myCustomFontLogo);

        textViewEntreLogin = findViewById(R.id.entrecomrede);
        textViewEntreLogin.setTypeface(myCustomFontLogo);

//        sing com google: - inicio do codigo
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mAuth = FirebaseAuth.getInstance();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        ImageView btnGoogleSignIn = findViewById(R.id.google_sign_in_id);
        btnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.google_sign_in_id:
                        signIn();
                        break;
                    // ...
                }
            }
        });
        loginFacebook = findViewById(R.id.button_facebookLogin_id);
        loginFacebook.setReadPermissions("email", "public_profile");
        callbackManager = CallbackManager.Factory.create();
        loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess (LoginResult loginResult){
                    Log.d(TAG, "facebook:onSuccess:" + loginResult);
                    handleFacebookAccessToken (loginResult.getAccessToken());
                    Intent intent = new Intent (loginFacebook.getContext(), PreferenceActivity.class);
                    startActivity(intent);
                    /*Bundle bundle = new Bundle();
                    bundle.putString(CHAVE_EMAIL, Profile.getCurrentProfile().getName());
                    intent.putExtras(bundle);*/
                }

                @Override
                public void onCancel () {
                    Log.d(TAG, "facebook:onCancel");
                }

                @Override
                public void onError (FacebookException error){
                    Log.d(TAG, "facebook:onError", error);
                }
        });
        //TODO registro
        TextView register = findViewById(R.id.register_now_id);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Register.class);
                startActivity(intent);
            }
        });

        //TODO LOGIN COM EMAIL E SENHA
        Button loginClicado = findViewById(R.id.login_button);
        loginClicado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LoginActivity.this, "Estou no loginClicado.OnClick", Toast.LENGTH_SHORT).show();
                final Intent intent = new Intent(v.getContext(), MainActivity.class);
                final Bundle bundle = new Bundle();

                final Button buttonLogin = findViewById(R.id.login_button);


                if (!emailDigitado.getText().toString().equals("") && !passwordDigitado.getText().toString().equals("")){
                    mAuth.signInWithEmailAndPassword(emailDigitado.getText().toString(), passwordDigitado.getText().toString())
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(LoginActivity.this, "Authentication successful! Enjoy our gallery!", Toast.LENGTH_LONG).show();
                                        bundle.putString(CHAVE_EMAIL, emailDigitado.getText().toString());
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    } else {
                                        emailDigitado.setTextColor(getResources().getColor(R.color.colorBlack));
                                        passwordDigitado.setTextColor(getResources().getColor(R.color.colorBlack));

                                        Snackbar.make(buttonLogin, "Invalid email and/or password.", Snackbar.LENGTH_INDEFINITE)
                                                .setAction("Got it.", new View.OnClickListener(){
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
        // Check if user is signed in (non-null) and update UI accordingly.
        if (mAuth.getCurrentUser() != null) {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            Intent intent = new Intent (this, PreferenceActivity.class);
            startActivity(intent);
        }
//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//        if (isLoggedIn) {
//            Intent intent = new Intent (this, PreferenceActivity.class);
//            startActivity(intent);
//        }
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
//                            Toast.makeText(LoginActivity.this, "A autenticacao foi feita com sucesso!", Toast.LENGTH_LONG).show();

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
        Toast.makeText(this, "Login realizado com sucesso! " + user.getEmail(), Toast.LENGTH_SHORT).show();
        irParaPreferencesGenerico();
    }

    private void signIn() {
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);

    }

    public void registerNow(View view) {
        Intent intent = new Intent(this,Register.class);
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

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

//sign in com o google. fim do codigo do google.

    public void irParaPreferencesGenerico() {
        Intent intent = new Intent(this, PreferenceActivity.class);
        startActivity(intent);
    }

}