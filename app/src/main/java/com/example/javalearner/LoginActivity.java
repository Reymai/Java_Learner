package com.example.javalearner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    Button mLoginBtn;
    EditText mEmailEdit;
    EditText mPasswordEdit;
    TextView mRegistrationTxtView;
    TextView mForgotPasswordTxtView;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginBtn = findViewById(R.id.loginBtn);
        mEmailEdit = findViewById(R.id.emailEdit);
        mPasswordEdit = findViewById(R.id.passwordEdit);
        mRegistrationTxtView = findViewById(R.id.registrationTxtView);
        mForgotPasswordTxtView = findViewById(R.id.forgotPasswordTxtView);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){

            startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
            finish();
        }


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();

                boolean passwordIsEmpty = password.isEmpty();
                boolean InvalidEmailPatterns = !Patterns.EMAIL_ADDRESS.matcher(email).matches();

                if (InvalidEmailPatterns) {
                    mEmailEdit.setFocusable(true);
                    mEmailEdit.setError("Invalid email address");
                } else if (passwordIsEmpty) {
                    mPasswordEdit.setFocusable(true);
                    mPasswordEdit.setError("Can't be empty");
                } else {
                    loginUser(email, password);
                }

            }
        });
        mRegistrationTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, EmailRegistrationActivity.class));
            }
        });
        mForgotPasswordTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgottenPasswordActivity.class));
            }
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Authentication Success.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finishAndRemoveTask();
    }
}
