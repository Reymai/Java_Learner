package com.example.javalearner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TabHost;
import android.widget.TextView;

public class Quest_activity extends AppCompatActivity {
    TabHost mTabHost;
    TextView Text1, Text2, Text3, Text4, Text5;
    CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10,cb11,cb12;
    Button b1, b2, b3, b4, b5;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_quest_actiity );

        b1 = findViewById( R.id.btn );
        b2 = findViewById( R.id.btn1 );
        b3 = findViewById( R.id.btn2 );
        b4 = findViewById( R.id.btn3 );
        b5 = findViewById( R.id.btn4 );

        cb1 = findViewById( R.id.a );
        cb2 = findViewById( R.id.a1 );
        cb3 = findViewById( R.id.a2 );
        cb4 = findViewById( R.id.a3 );

        Text1 = findViewById( R.id.txt );
        Text2 = findViewById( R.id.txt1 );
        Text3 = findViewById( R.id.btn2 );
        Text4 = findViewById( R.id.btn3 );
        Text5 = findViewById( R.id.btn4 );

        mTabHost = findViewById( R.id.TaskTab );
        final TabHost tabs = findViewById( R.id.TaskTab );

        tabs.setup();
        TabHost.TabSpec spec = tabs.newTabSpec( "tag1" );
        spec.setContent( R.id.tab1 );
        spec.setIndicator( "1" );
        tabs.addTab( spec );
        spec = tabs.newTabSpec( "tag2" );
        spec.setContent( R.id.tab2 );
        spec.setIndicator( "2" );
        tabs.addTab( spec );
        spec = tabs.newTabSpec( "tag3" );
        spec.setContent( R.id.tab3 );
        spec.setIndicator( "3" );
        tabs.addTab( spec );
        spec = tabs.newTabSpec( "tag4" );
        spec.setContent( R.id.tab4 );
        spec.setIndicator( "4" );
        tabs.addTab( spec );
        spec = tabs.newTabSpec( "tag5" );
        spec.setContent( R.id.tab5 );
        spec.setIndicator( "5" );
        tabs.addTab( spec );

        int quest = getIntent().getIntExtra( "2", 0 );
        if (quest == 2) {
            Text1.setText( "What command uses to print something" );
            cb1.setText( "System.out.println" );
            cb2.setText( "std::cout<<" );
            cb3.setText( "vivod" );
            cb4.setText( "println" );
            b1.setOnClickListener(new View.OnClickListener()
            {
               @Override
               public void onClick(View v){
                   tabs.setCurrentTab( 1 );
               }
            });
        }
    }
}