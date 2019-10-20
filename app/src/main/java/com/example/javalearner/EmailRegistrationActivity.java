package com.example.javalearner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EmailRegistrationActivity extends AppCompatActivity {

    Button mRegistrationBtn;
    EditText mEmailEdit;
    EditText mUsernameEdit;
    EditText mPasswordEdit;
    EditText mRepeatPasswordEdit;
    TextView mLoginTxtView;

    String error  = null;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

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

                String email = mEmailEdit.getText().toString();
                String username = mUsernameEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();
                String repeatPassword = mRepeatPasswordEdit.getText().toString();

                boolean InvalidEmailPatterns = !Patterns.EMAIL_ADDRESS.matcher(email).matches();
                boolean passwordToShort = password.length() < MIN_PASSWORD_LENGTH;

                if(InvalidEmailPatterns){
                    mEmailEdit.setFocusable(true);
                    mEmailEdit.setError("Invalid email address");
                } else if(passwordToShort){
                    mPasswordEdit.setFocusable(true);
                    mPasswordEdit.setError("Passwords length at least 6 and almost 32 characters");
                } else if(!password.equals(repeatPassword)){
                    mRepeatPasswordEdit.setFocusable(true);
                    mRepeatPasswordEdit.setError("Passwords don't match");
                } else if(username.isEmpty()){
                    mUsernameEdit.setFocusable(true);
                    mUsernameEdit.setError("Username is empty");
                }else {
                    registerUser(email, password, username);
                }
            }
        });
        mLoginTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmailRegistrationActivity.this, LoginActivity.class));
            }
        });
    }

    private void registerUser(final String email, String password, final String username) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            Toast.makeText(EmailRegistrationActivity.this, "Registration success", Toast.LENGTH_SHORT).show();
                            try {
                                registerToDatabase(username, email);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                                error = exception.toString();
                            }
                            if (error == null){
                                setUsername(username);
                                sendVerificationEmail();
                                startActivity(new Intent(EmailRegistrationActivity.this, MainMenuActivity.class));
                            }
                            else {
                                Toast.makeText(EmailRegistrationActivity.this, "Error with writing to database: " + error, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(EmailRegistrationActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EmailRegistrationActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerToDatabase(String username, String email) {
        Map<String, Object> user = new HashMap<>();
        user.put("Username", username);
        user.put("Level", 1);

        db.collection("users").document(email.toLowerCase())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(EmailRegistrationActivity.this, "Good for db", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EmailRegistrationActivity.this, "Error for db: "+e, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void sendVerificationEmail() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
//                            Toast.makeText(EmailRegistrationActivity.this, "Email sent", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EmailRegistrationActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
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
                            FirebaseUser user = mAuth.getCurrentUser();
                            String name = user.getDisplayName();
                        }
                    }
                });
    }
}
