package com.example.javalearner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LibraryThemeListActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction ;
    ContainerFragments lib_themes = new ContainerFragments();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_theme_list);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        LibraryThemeFragments lib_themes = new LibraryThemeFragments();

        fragmentTransaction.replace(R.id.Lib_container,lib_themes);
        fragmentTransaction.commit();

    }
    public void onBackPressed() {
        startActivity(new Intent(LibraryThemeListActivity.this, MainMenuActivity.class));
        finish();
    }
}
