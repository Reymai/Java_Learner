package com.example.javalearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuestChoose_Basics extends AppCompatActivity {
CardView tab1, tab2, tab3, tab4;
TextView t11, t12, t13, t21, t22, t23, t31, t32, t33, t41, t42, t43;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_quest_choose__basics );
        tab1 = findViewById( R.id.Tab1 );
        tab2 = findViewById( R.id.Tab2 );
        tab3 = findViewById( R.id.Tab3 );
        tab4 = findViewById( R.id.Tab4 );

        t11 = findViewById( R.id.QuestName );
        t12 = findViewById( R.id.QuestInfo );
        t13 = findViewById( R.id.QuestReward );

        t21 = findViewById( R.id.QuestName1 );
        t22 = findViewById( R.id.QuestInfo1 );
        t23 = findViewById( R.id.QuestReward1 );

        t31 = findViewById( R.id.QuestName2 );
        t32 = findViewById( R.id.QuestInfo2 );
        t33 = findViewById( R.id.QuestReward2 );

        t41 = findViewById( R.id.QuestName3 );
        t42 = findViewById( R.id.QuestInfo3 );
        t43 = findViewById( R.id.QuestReward3 );


        int theme = getIntent().getIntExtra( "Theme", 0 );

        if(theme == 1) {
            t11.setText( "Введение" );
            t12.setText( "Что такое Java?" );
            t13.setText( "exp: 50" );
            t21.setText( "Начало программы" );
            t22.setText( "Класс main" );
            t23.setText( "exp: 50" );
            t31.setText( "Hello world!" );
            t32.setText( "Что надо для вывода текста" );
            t33.setText( "exp: 50" );
            t41.setText( "Тест" );
            t42.setText( "1 проверка" );
            t43.setText( "exp: 100" );

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
            t11.setText( "Переменные" );
            t12.setText( "Основные типы переменных" );
            t13.setText( "exp: 50" );
            t21.setText( "Final и constant " );
            t22.setText( "" );
            t23.setText( "exp: 50" );
            t31.setText( "Математические операторы и инкремент с никрементом" );
            t32.setText( "Математика!" );
            t33.setText( "exp: 50" );
            t41.setText( "Операторы сравнения" );
            t42.setText( " == , <= , >= , != и другие" );
            t43.setText( "exp: 50" );


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
            t11.setText( "Cicles" );

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
            t11.setText( "Masives" );

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
            t11.setText( "Class" );

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
            t11.setText( "Other" );

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
