package com.example.javalearner;

import android.content.Intent;
import android.net.Uri;
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
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EmailRegistrationActivity extends AppCompatActivity {

    Button mRegistrationBtn;
    EditText mEmailEdit;
    EditText mUsernameEdit;
    EditText mPasswordEdit;
    EditText mRepeatPasswordEdit;
    TextView mLoginTxtView;

    String error  = null;

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

    private void registerUser(final String email, final String password, final String username) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            Toast.makeText(EmailRegistrationActivity.this, "Registration success", Toast.LENGTH_SHORT).show();
                            try {
                                registerToDatabase(username, email, password);
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

    private void registerToDatabase(String username, String email, String password) throws NoSuchAlgorithmException {
        Map<String, Object> user = new HashMap<>();
        user.put("Username", username);
        user.put("Password", sha256(password));
        user.put("XP", 0);
        user.put("Completed quests", 0);
        user.put("Language", Locale.getDefault().getLanguage());

        DatabaseHelper.DatabaseWrite("users", email.toLowerCase(), user);
    }

    private void sendVerificationEmail() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        user.sendEmailVerification()
                .addOnFailureListener(new OnFailureListener() {
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
                .setPhotoUri(Uri.parse("https://firebasestorage.googleapis.com/v0/b/java-learner-rvt.appspot.com/o/Avatars%2Favatar.png?alt=media&token=3eb503d7-0098-41a9-9128-f6979c160686"))
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

    private String sha256(String data){

        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(data, Charsets.UTF_8);
        HashCode sha256 = hasher.hash();

        return sha256.toString();
    }
}
