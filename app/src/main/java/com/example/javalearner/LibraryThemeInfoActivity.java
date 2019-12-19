package com.example.javalearner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class LibraryThemeInfoActivity extends AppCompatActivity {

    TextView mThemeInfoTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_theme_info);

        int theme = getIntent().getIntExtra("Lib_Theme", 0);
        mThemeInfoTxt = findViewById(R.id.ThemeInfoTxt);
        String[] names = new String[0];
        if (theme == 1) {

        }

        if(theme == 2){
            names = new String[]{""

            };
        }
            mThemeInfoTxt.setText("@strings/Level");
            for (String name : names) {
                mThemeInfoTxt.append(name + "\n\n\n\n\n");
            }
            
    }
}
