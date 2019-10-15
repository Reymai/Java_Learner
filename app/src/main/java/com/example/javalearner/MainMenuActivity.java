package com.example.javalearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainMenuActivity extends AppCompatActivity {

    ImageButton mLogoutBtn;
    TextView mUsername;
    ProgressBar mProgressBar;
    TextView mLVLTxt;


    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mLogoutBtn = findViewById(R.id.LogoutBtn);
        mUsername = findViewById(R.id.Username);
        mProgressBar = findViewById(R.id.progressBar);
        mLVLTxt = findViewById(R.id.LVLTxt);

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

                int level =Integer.parseInt((String) getResources().getText(Integer.parseInt("@string/Level")));

                mProgressBar.setMax(100);
                        currentProgress = mProgressBar.getProgress();
                        mProgressBar.setProgress(currentProgress+progress);
                        if(currentProgress>=progressMax){
                            mLVLTxt.setText(level = level + 1);
                            mProgressBar.setProgress(0);
                            mProgressBar.setMax(progressMax * 2);
                        }
            }
        });

    }
}