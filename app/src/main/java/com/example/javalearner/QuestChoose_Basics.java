package com.example.javalearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;
import java.util.ArrayList;

public class QuestChoose_Basics extends AppCompatActivity {
CardView tab1, tab2, tab3, tab4;
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
                Intent intent = new Intent(view.getContext(), Quest_actiity.class);
                view.getContext().startActivity(intent);}
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Quest_actiity.class);
                view.getContext().startActivity(intent);}
        });

        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Quest_actiity.class);
                view.getContext().startActivity(intent);}
        });

        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Quest_actiity.class);
                view.getContext().startActivity(intent);}
        });
    }
}
