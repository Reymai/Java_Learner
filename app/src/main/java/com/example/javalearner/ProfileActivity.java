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
    TextView mUsername;
    ImageView mProfileImg;

    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mExitBtn = findViewById(R.id.BackBtn);
        mUsername = findViewById(R.id.UsernameTxt);
        mProfileImg = findViewById(R.id.ProfileImg);

//        Uri photo = user.getPhotoUrl();
//        Glide.with(this)
//                .load(photo)
//                .into(mProfileImg);

        SharedPreferences sharedPref = ProfileActivity.this.getPreferences( Context.MODE_PRIVATE);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
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
