package com.example.javalearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.ConfigurationCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Locale;
import java.util.Set;
import java.util.Timer;

public class SettingsActivity extends AppCompatActivity {

    ImageButton mExitBtn;
    ImageButton mLanguageSwitch;
    Switch mSoundSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mExitBtn = findViewById(R.id.exitImgBtn);
        mLanguageSwitch = findViewById(R.id.LanguageImgBtn);
        mSoundSwitch = findViewById(R.id.SoundSwitch);

        Toast.makeText(SettingsActivity.this, ""+Locale.getDefault(), Toast.LENGTH_SHORT).show();

        mLanguageSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale locale = Locale.getDefault();
                String lang = locale.getLanguage();
                switch (lang){
                    case "ru":
                    case "ru_RU":{
                        locale = new Locale("en", "EN");
                        break;
                    }
                    case "en":
                    case "en_EN":{
                        locale = new Locale("lv", "LV");
                        break;
                    }
                    case "lv":
                    case "lv_LV":{
                        locale = new Locale("ru", "RU");
                        break;
                    }
                }

                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                recreate();
            }

        });

        //ToDo Sound Switch

        mExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, MainMenuActivity.class));
            }
        });
    }
}