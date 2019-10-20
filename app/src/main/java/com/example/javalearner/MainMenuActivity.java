package com.example.javalearner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
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

        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainMenuActivity.this, "You have been logout!!!", Toast.LENGTH_SHORT);
                startActivity(new Intent(MainMenuActivity.this, LoginActivity.class));
            }

        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            mUsername.setText(name);
        }

        mProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainMenuActivity.this, "+5 XP", Toast.LENGTH_SHORT).show();
                        int progress = 5;
                        int currentProgress;
                        int progressMax = mProgressBar.getMax();

//                String level = mLVLTxt.getText().toString();
//                int levelInt = Integer.valueOf(level);

                mProgressBar.setMax(100);
                        currentProgress = mProgressBar.getProgress();
                        mProgressBar.setProgress(currentProgress+progress);
//                        if(currentProgress>=progressMax){
//                            mLVLTxt.setText(levelInt = levelInt + 1);
//                            mProgressBar.setProgress(0);
//                            mProgressBar.setMax(progressMax * 2);
//                        }
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
}