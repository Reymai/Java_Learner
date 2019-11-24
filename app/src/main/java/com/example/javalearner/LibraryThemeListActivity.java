package com.example.javalearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LibraryThemeListActivity extends AppCompatActivity {

    ImageButton mBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_theme_list);

        mBackBtn = findViewById(R.id.BackButton);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LibraryThemeListActivity.this, MainMenuActivity.class));
            }
        });
    }
}
