package com.example.javalearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QuestChoose_Basics extends AppCompatActivity {
CardView tab1, tab2, tab3, tab4;
int quest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_quest_choose__basics );
        tab1 = findViewById( R.id.Tab1 );
        tab2 = findViewById( R.id.Tab2 );
        tab3 = findViewById( R.id.Tab3 );
        tab4 = findViewById( R.id.Tab4 );
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quest = 1;
                Intent intent = new Intent(view.getContext(), Quest_activity.class);
                view.getContext().startActivity(intent);}
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quest = 2;
                Intent intent = new Intent(view.getContext(), Quest_activity.class);
                intent.putExtra( "2" , 2 );
                view.getContext().startActivity(intent);}
        });

        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quest = 3;
                Intent intent = new Intent(view.getContext(), Quest_activity.class);
                view.getContext().startActivity(intent);}
        });

        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quest = 4;
                Intent intent = new Intent(view.getContext(), Quest_activity.class);
                view.getContext().startActivity(intent);}
        });
    }
}
