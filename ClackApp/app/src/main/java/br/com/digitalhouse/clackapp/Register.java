package br.com.digitalhouse.clackapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import br.com.digitalhouse.clackapp.R;

public class Register extends AppCompatActivity {

    private static final String TAG = "Register";
    private TextInputEditText firstNameInput;
    private TextInputEditText emailInput;
    private TextInputEditText passwordInput;
    private TextInputEditText passwordConfirmInput;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth = FirebaseAuth.getInstance();



        Button register = findViewById(R.id.register_button_id);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarUsuario();
            }

        });
    }

    public void goToHomeActivity() {
        Intent intent = new Intent(getApplicationContext(), PreferenceActivity.class);
        startActivity(intent);
    }

    private void criarUsuario() {
        firstNameInput = findViewById(R.id.edit_text_firstname_id);
        emailInput = findViewById(R.id.edit_text_email_id);
        passwordInput = findViewById(R.id.edit_text_password_id);
        passwordConfirmInput = findViewById(R.id.edit_text_password_confirm_id);

        if (passwordConfirmInput.getText().toString().equals(passwordInput.getText().toString())) {
            mAuth.createUserWithEmailAndPassword(emailInput.getText().toString(), passwordInput.getText().toString())
                    .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //criarUsuario();
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getInstance().getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(firstNameInput.getText().toString())
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "User profile updated.");
                                                    goToHomeActivity();
                                                }
                                            }
                                        });

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Register.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            goToHomeActivity();

        } else {
            Toast.makeText(Register.this, "Both passwords must match.", Toast.LENGTH_SHORT).show();
        }
    }
}