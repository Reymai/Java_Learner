package com.example.javalearner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    Button mRegistrationBtn;
    EditText mEmailEdit;
    EditText mUsernameEdit;
    EditText mPasswordEdit;
    EditText mRepeatPasswordEdit;
    TextView mLoginTxtView;

    private FirebaseAuth mAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mRegistrationBtn = findViewById(R.id.registrationBtn);
        mEmailEdit = findViewById(R.id.emailEdit);
        mUsernameEdit = findViewById(R.id.usernameEdit);
        mPasswordEdit = findViewById(R.id.passwordEdit);
        mRepeatPasswordEdit = findViewById(R.id.passwordAgainEdit);
        mLoginTxtView = findViewById(R.id.loginTxtView);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final byte MIN_PASSWORD_LENGTH = 6;
                final byte MAX_PASSWORD_LENGTH = 32;

                String email = mEmailEdit.getText().toString();
                String username = mUsernameEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();
                String repeatPassword = mRepeatPasswordEdit.getText().toString();

                boolean InvalidEmailPatterns = !Patterns.EMAIL_ADDRESS.matcher(email).matches();
                boolean passwordToShort = password.length() < MIN_PASSWORD_LENGTH;
                boolean passwordToLong  = password.length() > MAX_PASSWORD_LENGTH;
                boolean usernameContainsPidor = username.contains("pidor");
                boolean emailContainsPidor = email.contains("pidor");

                if(InvalidEmailPatterns){
                    mEmailEdit.setFocusable(true);
                    mEmailEdit.setError("Invalid email address");
                } else if(passwordToShort || passwordToLong){
                    mPasswordEdit.setFocusable(true);
                    mPasswordEdit.setError("Passwords length at least 6 and almost 32 characters");
                } else if(!password.equals(repeatPassword)){
                    mRepeatPasswordEdit.setFocusable(true);
                    mRepeatPasswordEdit.setError("Passwords don't match");
                } else if(usernameContainsPidor){
                  mUsernameEdit.setFocusable(true);
                  mUsernameEdit.setError("Sam Pidor!");
                } else if(emailContainsPidor){
                  mUsernameEdit.setFocusable(true);
                  mUsernameEdit.setError("Sam Pidor!");
                } else {
                    registerUser(email, password, username);
                }
            }
        });
        mLoginTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
    }

    private void registerUser(final String email, String password, final String username) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Toast.makeText(RegistrationActivity.this, "Authentication success", Toast.LENGTH_SHORT).show();
                            setUsername(username);
                        } else {
                            // If sign in fails
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                        //TODO Going to the next activity
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistrationActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUsername(final String username) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.println(Log.INFO,username,"Username is changed");

                            FirebaseUser user = mAuth.getCurrentUser();
                            String name = user.getDisplayName();

                            Toast.makeText(RegistrationActivity.this, "Welcome, " + name, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
