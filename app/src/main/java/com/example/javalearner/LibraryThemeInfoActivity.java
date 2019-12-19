package com.example.javalearner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.logging.Level;

public class LibraryThemeInfoActivity extends AppCompatActivity {

    TextView mThemeInfoTxt;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_theme_info);

        int theme = getIntent().getIntExtra("Lib_Theme", 0);
        mThemeInfoTxt = findViewById(R.id.ThemeInfoTxt);
        if (theme == 1) {
            String java_basic_1 = getString(R.string.Java_Basic);
            String java_basic_2 = getString(R.string.Java_Basic_2);
            String java_basic_3 = getString(R.string.Java_Basic_3);
            String java_basic_4 = getString(R.string.Java_Basic_4);
            String java_basic_5 = getString(R.string.Java_Basic_5);
            String java_basic_6 = getString(R.string.Java_Basic_6);
            String java_basic_7 = getString(R.string.Java_Basic_7);
            String java_basic_8 = getString(R.string.Java_Basic_8);

            mThemeInfoTxt.setText(java_basic_1 + "\n\n" + java_basic_2 + "\n\n" + java_basic_3 + "\n\n" + java_basic_4 + "\n\n" + java_basic_5 + "\n\n" + java_basic_6 + "\n\n" + java_basic_7 + "\n\n" + java_basic_8);
        }

        if(theme == 2){
            String java_vao_1 = getString(R.string.Java_VaO);
            String java_vao_2 = getString(R.string.Java_VaO_2);
            String java_vao_3 = getString(R.string.Java_VaO_3);
            String java_vao_4 = getString(R.string.Java_VaO_4);
            String java_vao_5 = getString(R.string.Java_VaO_5);
            String java_vao_6 = getString(R.string.Java_VaO_6);

            mThemeInfoTxt.setText(java_vao_1 + "\n\n" + java_vao_2 + "\n\n" + java_vao_3 + "\n\n" + java_vao_4 + "\n\n" + java_vao_5 +"\n\n" + java_vao_6);
            }

        if(theme == 3){
            String java_circle = getString(R.string.Java_Circle);
            String java_circle_2 = getString(R.string.Java_Circle_2);
            String java_circle_3 = getString(R.string.Java_Circle_3);
            String java_circle_4 = getString(R.string.Java_Circle_4);
            String java_circle_5 = getString(R.string.Java_Circle_5);

            mThemeInfoTxt.setText(java_circle + "\n\n" + java_circle_2 + "\n\n" + java_circle_3 + "\n\n" + java_circle_4 + "\n\n" + java_circle_5);
        }

        if(theme == 4){
            String java_massivs = getString(R.string.Java_Massivs);
            String java_massivs2 = getString(R.string.Java_Massivs_2);
            String java_massivs3= getString(R.string.Java_Massivs_3);
            String java_massivs4 = getString(R.string.Java_Massivs_4);
            String java_massivs5 = getString(R.string.Java_Massivs_5);

            mThemeInfoTxt.setText(java_massivs + "\n\n" + java_massivs2 + "\n\n" + java_massivs3 + "\n\n" + java_massivs4 + "\n\n" + java_massivs5);
        }

        if(theme == 5){
            String java_class = getString(R.string.Java_Class);
            String java_class2 = getString(R.string.Java_Class_2);
            String java_class3= getString(R.string.Java_Class_3);
            String java_class4 = getString(R.string.Java_Class_4);
            String java_class5 = getString(R.string.Java_Class_5);
            String java_class6 = getString(R.string.Java_Class_6);

            mThemeInfoTxt.setText(java_class + "\n\n" + java_class2 + "\n\n" + java_class3 + "\n\n" + java_class4 + "\n\n" + java_class5 + "\n\n" + java_class6);
        }
    }
}
