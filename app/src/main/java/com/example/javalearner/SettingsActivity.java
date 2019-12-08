package com.example.javalearner;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;



public class SettingsActivity extends AppCompatActivity {

    ImageButton mExitBtn;
    ImageButton mLanguageSwitch;
    Switch mSoundSwitch;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mExitBtn = findViewById(R.id.exitImgBtn);
        mLanguageSwitch = findViewById(R.id.LanguageImgBtn);
        mSoundSwitch = findViewById(R.id.SoundSwitch);


        Configuration configuration = getResources().getConfiguration();
        String language = configuration.getLocales().toString();
//        configuration.setLocale(Locale.forLanguageTag(language))

//        mLanguageSwitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String dbLang = DatabaseHelper.DatabaseRead("users", user.getEmail(), "Language");
//                Locale locale = Locale.getDefault();
//                String lang = locale.getLanguage();
//                switch (lang){
//                    case "ru":
//                    case "ru_RU":{
//                        locale = new Locale("en", "US");
//                        break;
//                    }
//                    case "en":
//                    case "en_US":{
//                        locale = new Locale("lv", "LV");
//                        break;
//                    }
//                    case "lv":
//                    case "lv_LV":{
//                        locale = new Locale("ru", "RU");
//                        break;
//                    }
//                }
//
//                Locale.setDefault(locale);
//                LocaleList localeList = new LocaleList(locale);
//                Configuration config = new Configuration();
//                config.locale = locale;
//                DatabaseHelper.DatabaseUpdateField("users", user.getEmail().toLowerCase(), "Language", config.getLocales().toString());
//                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
////                Toast.makeText(SettingsActivity.this, ""+config , Toast.LENGTH_LONG).show();
//                recreate();
//            }
//        });

        //ToDo Sound Switch

        mExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, MainMenuActivity.class));
            }
        });
    }

}