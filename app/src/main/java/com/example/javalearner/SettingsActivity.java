package com.example.javalearner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

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

        Configuration configuration = getResources().getConfiguration();

        final SharedPreferences sharedPref = SettingsActivity.this.getPreferences(Context.MODE_PRIVATE);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (!LocaleHelper.checkLocaleSharedPreferences("Locale", sharedPref, this)){
            super.recreate();
        }

        mLanguageSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale locale = Locale.getDefault();
                String language = locale.getLanguage();

                switch (language){
                    case "ru":
                    case "ru_RU":{
                        locale = new Locale("en", "US");
                        break;
                    }
                    case "en":
                    case "en_US":{
                        locale = new Locale("lv", "LV");
                        break;
                    }
                    case "lv":
                    case "lv_LV":{
                        locale = new Locale("ru", "RU");
                        break;
                    }
                }

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("Locale", locale.getLanguage());
                editor.commit();
                LocaleHelper.changeLanguage(locale, SettingsActivity.this);

                recreate();
            }
        });

        //ToDo Sound Switch

        mExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale locale = Locale.getDefault();
                DatabaseHelper.DatabaseUpdateField("users", user.getEmail().toLowerCase(), "Language", locale.getLanguage());
                startActivity(new Intent(SettingsActivity.this, MainMenuActivity.class));
                finish();
            }
        });
    }
}