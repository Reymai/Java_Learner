package com.example.javalearner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;


public class MainMenuActivity extends AppCompatActivity {

    ImageButton mLogoutBtn;
    ImageButton mSettingsBtn;
    ImageButton mLibraryBtn;
    ImageButton mProfileBtn;
    TextView mUsername;
    TextView mLVLTxt;
    ProgressBar mProgressBar;
    ProgressBar mMainMenuPB;
    ImageView mProfileImg;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


//    private StorageReference mStorageRef;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mLogoutBtn = findViewById(R.id.LogoutBtn);
        mUsername = findViewById(R.id.Username);
        mProgressBar = findViewById(R.id.progressBar);
        mLVLTxt = findViewById(R.id.LVLTxt);
        mSettingsBtn = findViewById(R.id.SettingsBtn);
//        mStorageRef = FirebaseStorage.getInstance().getReference();
        mProfileImg = findViewById(R.id.ProfileImg);
        mLibraryBtn = findViewById(R.id.LibraryBtn);
        mProfileBtn = findViewById(R.id.ProfileBtn);
        mMainMenuPB = findViewById(R.id.MainMenuPB);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            mUsername.setText(name);
        }else{
            signOut();
        }

        //Progress
        mMainMenuPB.setVisibility(View.VISIBLE);
        int progress = Integer.parseInt(DatabaseHelper.DatabaseRead("users", user.getEmail(), "XP"));
        mMainMenuPB.setVisibility(View.GONE);
        mProgressBar.setMax(100);
        mProgressBar.setProgress(progress);
        Toast.makeText(MainMenuActivity.this, "Progress: "+progress, Toast.LENGTH_SHORT).show();



        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        mProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, SettingsActivity.class));
            }
        });

        mLibraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, LibraryThemeListActivity.class));
            }
        });

        mProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, ProfileActivity.class));
            }
        });


    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(MainMenuActivity.this, "You have been logout", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainMenuActivity.this, LoginActivity.class));
    }

    @Override
    public void onStart(){
        super.onStart();

    }

    @Override
    public void onBackPressed() {

    }
}