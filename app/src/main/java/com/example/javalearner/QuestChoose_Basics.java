package com.example.javalearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuestChoose_Basics extends AppCompatActivity {
CardView tab1, tab2, tab3, tab4;
TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_quest_choose__basics );
        tab1 = findViewById( R.id.Tab1 );
        tab2 = findViewById( R.id.Tab2 );
        tab3 = findViewById( R.id.Tab3 );
        tab4 = findViewById( R.id.Tab4 );
        t1 = findViewById( R.id.QuestName );

        int theme = getIntent().getIntExtra( "Theme", 0 );

        if(theme == 1) {
            t1.setText( "Basics" );

            tab1.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 1 );
                    intent.putExtra( "Quest", 1 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab2.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 1 );
                    intent.putExtra( "Quest", 2 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab3.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 1 );
                    intent.putExtra( "Quest", 3 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab4.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 1 );
                    intent.putExtra( "Quest", 4 );
                    view.getContext().startActivity( intent );
                }
            } );
        }
        if(theme == 2) {
            t1.setText( "Values" );

            tab1.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 2 );
                    intent.putExtra( "Quest", 1 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab2.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 2 );
                    intent.putExtra( "Quest", 2 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab3.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 2 );
                    intent.putExtra( "Quest", 3 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab4.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 2 );
                    intent.putExtra( "Quest", 4 );
                    view.getContext().startActivity( intent );
                }
            } );
        }
        if(theme == 3) {
            t1.setText( "Cicles" );

            tab1.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 3 );
                    intent.putExtra( "Quest", 1 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab2.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 3 );
                    intent.putExtra( "Quest", 2 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab3.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 3 );
                    intent.putExtra( "Quest", 3 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab4.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 3 );
                    intent.putExtra( "Quest", 4 );
                    view.getContext().startActivity( intent );
                }
            } );
        }
        if(theme == 4) {
            t1.setText( "Masives" );

            tab1.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 4 );
                    intent.putExtra( "Quest", 1 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab2.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 4 );
                    intent.putExtra( "Quest", 2 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab3.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 4 );
                    intent.putExtra( "Quest", 3 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab4.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 4 );
                    intent.putExtra( "Quest", 4 );
                    view.getContext().startActivity( intent );
                }
            } );
        }
        if(theme == 5) {
            t1.setText( "Class" );

            tab1.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 5 );
                    intent.putExtra( "Quest", 1 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab2.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 5 );
                    intent.putExtra( "Quest", 2 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab3.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 5 );
                    intent.putExtra( "Quest", 3 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab4.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 5 );
                    intent.putExtra( "Quest", 4 );
                    view.getContext().startActivity( intent );
                }
            } );
        }
        if(theme == 6) {
            t1.setText( "Other" );

            tab1.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 6 );
                    intent.putExtra( "Quest", 1 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab2.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 6 );
                    intent.putExtra( "Quest", 2 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab3.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 6 );
                    intent.putExtra( "Quest", 3 );
                    view.getContext().startActivity( intent );
                }
            } );

            tab4.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( view.getContext(), Quest_activity.class );
                    intent.putExtra( "Quest_Theme", 6 );
                    intent.putExtra( "Quest", 4 );
                    view.getContext().startActivity( intent );
                }
            } );
        }
    }
}
