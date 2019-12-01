package com.example.javalearner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;
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
        //Language
            final SharedPreferences sharedPref = MainMenuActivity.this.getPreferences(Context.MODE_PRIVATE);
            final String dbLang = DatabaseHelper.DatabaseRead("users", user.getEmail(), "Language");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Locale locale = new Locale(dbLang);

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("Locale", locale.getLanguage());
            editor.commit();

            if (!LocaleHelper.checkLocaleSharedPreferences("Locale", sharedPref, this)){
                super.recreate();
            }
        //Progress
        mMainMenuPB.setVisibility(View.VISIBLE);
        try {
            int progress = Integer.parseInt(DatabaseHelper.DatabaseRead("users", user.getEmail(), "XP"));
            mMainMenuPB.setVisibility(View.GONE);
            mProgressBar.setMax(100);
            mProgressBar.setProgress(progress);
            Toast.makeText(MainMenuActivity.this, "Progress: "+progress, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e("Database error:", ""+e);
//            recreate();
        }

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
                finish();
            }
        });

        mLibraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, LibraryThemeListActivity.class));
                finish();
            }
        });

        mProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, ProfileActivity.class));
                finish();
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
        finishAndRemoveTask();
    }
}