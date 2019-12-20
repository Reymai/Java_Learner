package com.example.javalearner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    ImageButton mExitBtn;
    TextView mUsername, mInfo;
    ImageView mProfileImg;

    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mExitBtn = findViewById(R.id.BackBtn);
        mUsername = findViewById(R.id.UsernameTxt);
        mInfo = findViewById( R.id.UserInfo );
        mProfileImg = findViewById(R.id.ProfileImg);

        int progress = 0;

        Uri photo = user.getPhotoUrl();
        Glide.with(this)
                .load(photo)
                .into(mProfileImg);

        SharedPreferences sharedPref = ProfileActivity.this.getPreferences( Context.MODE_PRIVATE);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String dbxp = DatabaseHelper.DatabaseRead("users", user.getEmail(), "XP");
            String dbcompleted = DatabaseHelper.DatabaseRead( "users", user.getEmail(), "Completed quests" );
            DatabaseHelper.DatabaseListen("users", user.getEmail(), "XP", sharedPref);

            try {
                progress = Integer.parseInt(sharedPref.getString("XP", "0"));
            }catch (Exception e){
                Log.e("PROGRESS", ""+e);
            }

            String level = Integer.toString(ProgressHelper.levelCounter(progress));
            int max = ProgressHelper.getMaxExperience(Integer.parseInt(level));

            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            mInfo.setText( "XP: " + dbxp + "\nLevel: " + level + "\nCompleted quests: " + dbcompleted );
            mUsername.setText(name);
        }

        mExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MainMenuActivity.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(ProfileActivity.this, MainMenuActivity.class));
        finish();
    }
}
