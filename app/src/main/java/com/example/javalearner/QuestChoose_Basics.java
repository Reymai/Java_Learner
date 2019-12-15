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

/*
        List<QuestTab> questList;
        RecyclerView recyclerView;

        recyclerView = findViewById(R.id.Recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        questList = new ArrayList<>();

        questList.add(
                new QuestTab(
                        "First Words",
                        "exp: 50",
                        "Print out words Hello world!"));

        questList.add(
                new QuestTab(
                        "Main quest",
                        "Become a best programmer",
                        "exp:over1"));

        questList.add(
                new QuestTab(
                        "LIFE QUEST",
                        "Watch Rick and Morty 5 season",
                        "exp:over9000"));

        questList.add(
                new QuestTab(
                        "BE CUTE!~",
                        "Do something",
                        "exp:0 \n cute flowers"));
        questList.add(
                new QuestTab(
                        "U can do it!",
                        "U can!",
                        "exp: Max"));
        questList.add(
                new QuestTab(
                        "Looser",
                        "Just dont do anything",
                        "exp:-over9000"));
        questList.add(
                new QuestTab(
                        "Elonmasaskisaski",
                        "U hear that?!",
                        "exp:0"));

        QuestTabAdapter adapter = new QuestTabAdapter(this,questList);

        adapter.setOnItemClickListener(new QuestTabAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(QuestTab quest) {
                    Intent intent = new Intent( QuestChoose_Basics.this, MainActivity.class );
                    startActivity( intent );
            }
        });

        recyclerView.setAdapter(adapter);

 */


    }
}
