package com.example.javalearner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TabHost;

public class Quest_actiity extends AppCompatActivity {
    TabHost mTabHost;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_quest_actiity );

        b1=findViewById( R.id.btn1 );

        mTabHost = findViewById(R.id.TaskTab);
        TabHost tabs = findViewById(R.id.TaskTab);

        tabs.setup();
        TabHost.TabSpec spec = tabs.newTabSpec("tag1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("1");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("2");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("tag3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("3");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("tag4");
        spec.setContent(R.id.tab4);
        spec.setIndicator("4");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("tag5");
        spec.setContent(R.id.tab5);
        spec.setIndicator("5");
        tabs.addTab(spec);


    }
}
