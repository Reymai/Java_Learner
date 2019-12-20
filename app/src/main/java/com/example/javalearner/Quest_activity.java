package com.example.javalearner;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class Quest_activity extends AppCompatActivity {
    TabHost mTabHost;
    TextView Text1, Text2, Text3, Text4, Text5, Text6, Text7;
    CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10, cb11, cb12;
    Button b1, b2, b3, b4, b5;
    ImageView img, img1, img2, img3;
    RadioButton rb1, rb2, rb3;
	private static Map dbAnswer;

	private static FirebaseFirestore db = FirebaseFirestore.getInstance();

	@Override
    protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quest_actiity);

		img = findViewById( R.id.img );
		img1 = findViewById( R.id.img1 );
		img2 = findViewById( R.id.img2 );
		img3 = findViewById( R.id.img3 );

		b1 = findViewById( R.id.btn );
		b2 = findViewById( R.id.btn1 );
		b3 = findViewById( R.id.btn2 );
		b4 = findViewById( R.id.btn3 );
		b5 = findViewById( R.id.btn5 );

		rb1 = findViewById( R.id.r );
		rb2 = findViewById( R.id.r1 );
		rb3 = findViewById( R.id.r2 );

		cb1 = findViewById( R.id.a );
		cb2 = findViewById( R.id.a1 );
		cb3 = findViewById( R.id.a2 );
		cb4 = findViewById( R.id.a3 );
		cb5 = findViewById( R.id.a4 );
		cb6 = findViewById( R.id.a5 );
		cb7 = findViewById( R.id.a6 );
		cb8 = findViewById( R.id.a7 );
		cb9 = findViewById( R.id.a8 );
		cb10 = findViewById( R.id.a9 );
		cb11 = findViewById( R.id.a10 );
		cb12 = findViewById( R.id.a11 );

		Text1 = findViewById( R.id.txt );
		Text2 = findViewById( R.id.txt1 );
		Text3 = findViewById( R.id.txt2 );
		Text4 = findViewById( R.id.txt3 );
		Text5 = findViewById( R.id.txt5 );
		Text6 = findViewById( R.id.txt6 );
		Text7 = findViewById( R.id.txt7 );

		mTabHost = findViewById( R.id.TaskTab );

		final TabHost tabs = findViewById( R.id.TaskTab );

		final int theme = getIntent().getIntExtra("Quest_Theme", 0);
		int quest = getIntent().getIntExtra("Quest", 0);

		if(theme == 1) {
			if (quest == 1) {
				getResultFromDB( "1" );

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
				spec = tabs.newTabSpec( "tag4" );
				spec.setContent( R.id.tab4 );
				spec.setIndicator( "4" );
				spec = tabs.newTabSpec( "tag5" );
				spec.setContent( R.id.tab5 );
				spec.setIndicator( "5" );
				spec = tabs.newTabSpec( "tag6" );
				spec.setContent( R.id.tab6 );
				spec.setIndicator( "6" );
				spec = tabs.newTabSpec( "tag7" );
				spec.setContent( R.id.tab7 );
				spec.setIndicator( "7" );
				spec = tabs.newTabSpec( "tag8" );
				spec.setContent( R.id.tab8 );
				spec.setIndicator( "8" );

				Text1.setText( "Java представляет собой язык программирования и платформу вычислений. " +
						"Существует множество приложений и веб-сайтов, которые не работают при отсутствии установленной Java, и с каждым днем число таких веб-сайтов и приложений увеличивается." +
						"Java отличается быстротой, высоким уровнем защиты и надежностью. " +
						"От портативных компьютеров до центров данных, от игровых консолей до суперкомпьютеров, используемых для научных разработок, от сотовых телефонов до сети Интернет — Java повсюду! " +
						"Java можно загрузить бесплатно." +
						"Если вы разрабатываете встроенное или бытовое устройство и хотите использовать в нем технологии Java, свяжитесь со специалистами Oracle и получите подробную информацию об интеграции Java в различные типы устройств." );
				b1.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						tabs.setCurrentTab( 1 );
					}
				} );

				Text2.setText( "Как можно описать java?" );
				cb1.setText( "Бесплатный и строго типизированный объектно-ориентированный язык программирования, предназначенный для множества устройств" );
				cb2.setText( "Язык программирования для суперкомпютеров" );
				cb3.setText( "Проффесиональный редактор текста" );
				cb4.setText( "Платная программа для программирования" );

				b2.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String answerCb1 = dbAnswer.get( "cb1" ).toString();
						String answerCb2 = dbAnswer.get( "cb2" ).toString();
						String answerCb3 = dbAnswer.get( "cb3" ).toString();
						String answerCb4 = dbAnswer.get( "cb4" ).toString();

						String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
						Log.i( "answer sum", "" + answerSum );

						StringBuffer result = new StringBuffer();
						result.append( cb1.isChecked() );
						result.append( cb2.isChecked() );
						result.append( cb3.isChecked() );
						result.append( cb4.isChecked() );
						Log.i( "results", "" + result );

						if (answerSum.equals( result.toString() )) {
							Intent intent = new Intent( v.getContext(), MainMenuActivity.class );
							v.getContext().startActivity( intent );
						}

					}
				} );
			}
			if (quest == 2) {

				getResultFromDB( "2" );

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
				spec = tabs.newTabSpec( "tag4" );
				spec.setContent( R.id.tab4 );
				spec.setIndicator( "4" );
				spec = tabs.newTabSpec( "tag5" );
				spec.setContent( R.id.tab5 );
				spec.setIndicator( "5" );
				spec = tabs.newTabSpec( "tag6" );
				spec.setContent( R.id.tab6 );
				spec.setIndicator( "6" );
				spec = tabs.newTabSpec( "tag7" );
				spec.setContent( R.id.tab7 );
				spec.setIndicator( "7" );
				spec = tabs.newTabSpec( "tag8" );
				spec.setContent( R.id.tab8 );
				spec.setIndicator( "8" );

				Text1.setText( "Каждая программа нуждается в объекте - что либо, обладающее определённым состоянием и поведением, имеющая определенные свойства (атрибуты) и операции над ними (методы)" +
						"Класс — может быть определен как шаблон, который описывает поведение объекта. Метод — является в основном поведением. Класс может содержать несколько методов. Именно в методах логически записанные данные манипулируют и выполняют все действия." );

				Drawable myDrawable = getResources().getDrawable( R.drawable.class_1_2_tak );
				img.setImageDrawable( myDrawable );

				b1.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						tabs.setCurrentTab( 1 );
					}
				} );

				Text2.setText( "Как можно описать объект в java?" );
				cb1.setText( "Человек какой либо професии" );
				cb2.setText( "Не живой объект который существует" );
				cb3.setText( "Что либо обладающее свойствами, шаблонами и поведением" );
				cb4.setText( "Животное" );

				b2.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String answerCb1 = dbAnswer.get( "cb1" ).toString();
						String answerCb2 = dbAnswer.get( "cb2" ).toString();
						String answerCb3 = dbAnswer.get( "cb3" ).toString();
						String answerCb4 = dbAnswer.get( "cb4" ).toString();

						String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
						Log.i( "answer sum", "" + answerSum );

						StringBuffer result = new StringBuffer();
						result.append( cb1.isEnabled() );
						result.append( cb2.isEnabled() );
						result.append( cb3.isEnabled() );
						result.append( cb4.isEnabled() );
						Log.i( "results", "" + result );

						if (answerSum.equals( result.toString() )) {
							Intent intent = new Intent( v.getContext(), MainMenuActivity.class );
							v.getContext().startActivity( intent );
						}

					}
				} );
			}
			if (quest == 3) {

				getResultFromDB( "3" );

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
				spec.setIndicator( "4" );
				spec = tabs.newTabSpec( "tag4" );
				spec.setContent( R.id.tab4 );
				spec.setIndicator( "3" );
				spec = tabs.newTabSpec( "tag5" );
				spec.setContent( R.id.tab5 );
				spec.setIndicator( "5" );
				spec = tabs.newTabSpec( "tag6" );
				spec.setContent( R.id.tab6 );
				spec.setIndicator( "6" );
				spec = tabs.newTabSpec( "tag7" );
				spec.setContent( R.id.tab7 );
				spec.setIndicator( "7" );
				spec = tabs.newTabSpec( "tag8" );
				spec.setContent( R.id.tab8 );
				spec.setIndicator( "8" );

				Text1.setText( "Первая программа - то с чего начинается всё! Для каждого 1 программа одинакова - Привет мир!" +
						"для запуска программны мы должны создать метод main , далее создается тело кода с выводом текста по виду оно должно выглядеть примерно так" );

				Drawable myDrawable = getResources().getDrawable( R.drawable.code1 );
				img.setImageDrawable( myDrawable );

				b1.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						tabs.setCurrentTab( 1 );
					}
				} );

				Text2.setText( "Что выведет предыдущий код?" );
				cb1.setText( "Hello world!" );
				cb2.setText( "error" );
				cb3.setText( "Hello\nworld\n!" );
				cb4.setText( "Hello world! в скобках" );

				b2.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String answerCb1 = dbAnswer.get( "cb1" ).toString();
						String answerCb2 = dbAnswer.get( "cb2" ).toString();
						String answerCb3 = dbAnswer.get( "cb3" ).toString();
						String answerCb4 = dbAnswer.get( "cb4" ).toString();

						String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
						Log.i( "answer sum", "" + answerSum );

						StringBuffer result = new StringBuffer();
						result.append( cb1.isChecked() );
						result.append( cb2.isChecked() );
						result.append( cb3.isChecked() );
						result.append( cb4.isChecked() );
						Log.i( "results", "" + result );

						if (answerSum.equals( result.toString() )) {
							Intent intent = new Intent( v.getContext(), MainMenuActivity.class );
							v.getContext().startActivity( intent );
						}
					}
				} );
			}
			if(quest == 4) {

				getResultFromDB( "4" );

				tabs.setup();
				TabHost.TabSpec spec = tabs.newTabSpec( "tag1" );
				spec.setContent( R.id.tab1 );
				spec.setIndicator( "3" );
				spec = tabs.newTabSpec( "tag2" );
				spec.setContent( R.id.tab2 );
				spec.setIndicator( "1" );
				tabs.addTab( spec );
				spec = tabs.newTabSpec( "tag3" );
				spec.setContent( R.id.tab3 );
				spec.setIndicator( "4" );
				spec = tabs.newTabSpec( "tag4" );
				spec.setContent( R.id.tab4 );
				spec.setIndicator( "2" );
				tabs.addTab( spec );
				spec = tabs.newTabSpec( "tag5" );
				spec.setContent( R.id.tab5 );
				spec.setIndicator( "5" );
				spec = tabs.newTabSpec( "tag6" );
				spec.setContent( R.id.tab6 );
				spec.setIndicator( "6" );
				spec = tabs.newTabSpec( "tag7" );
				spec.setContent( R.id.tab7 );
				spec.setIndicator( "7" );
				spec = tabs.newTabSpec( "tag8" );
				spec.setContent( R.id.tab8 );
				spec.setIndicator( "8" );

				Text2.setText( "Что бы вывести что то на экран используется команда" );
				cb1.setText( "std::cout<<" );
				cb2.setText( "println" );
				cb3.setText( "System.out.println()" );
				cb4.setText( "print" );

				b2.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String answerCb1 = dbAnswer.get( "cb1" ).toString();
						String answerCb2 = dbAnswer.get( "cb2" ).toString();
						String answerCb3 = dbAnswer.get( "cb3" ).toString();
						String answerCb4 = dbAnswer.get( "cb4" ).toString();

						String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
						Log.i( "answer sum", "" + answerSum );

						StringBuffer result = new StringBuffer();
						result.append( cb1.isChecked() );
						result.append( cb2.isChecked() );
						result.append( cb3.isChecked() );
						result.append( cb4.isChecked() );
						Log.i( "results", "" + result );

						if (answerSum.equals( result.toString() )) {
							tabs.setCurrentTab( 1 );
						}
					}
				} );

				Text4.setText( "Какой мнетод обязателен в любой программе" );
				cb5.setText( "void" );
				cb6.setText( "return" );
				cb7.setText( "System." );
				cb8.setText( "main" );

				b4.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String answerCb1 = dbAnswer.get( "cb5" ).toString();
						String answerCb2 = dbAnswer.get( "cb6" ).toString();
						String answerCb3 = dbAnswer.get( "cb7" ).toString();
						String answerCb4 = dbAnswer.get( "cb8" ).toString();

						String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
						Log.i( "answer sum", "" + answerSum );

						StringBuffer result = new StringBuffer();
						result.append( cb5.isChecked() );
						result.append( cb6.isChecked() );
						result.append( cb7.isChecked() );
						result.append( cb8.isChecked() );
						Log.i( "results", "" + result );

						if (answerSum.equals( result.toString() )) {
							Intent intent = new Intent( v.getContext(), MainMenuActivity.class );
							v.getContext().startActivity( intent );
						}
					}
				} );
			}
		}
		if(theme == 2) {
			if (quest == 1) {
				getResultFromDB( "5" );

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
				spec = tabs.newTabSpec( "tag4" );
				spec.setContent( R.id.tab4 );
				spec.setIndicator( "3" );
				tabs.addTab( spec );
				spec = tabs.newTabSpec( "tag5" );
				spec.setContent( R.id.tab5 );
				spec.setIndicator( "5" );
				spec = tabs.newTabSpec( "tag6" );
				spec.setContent( R.id.tab6 );
				spec.setIndicator( "6" );
				spec = tabs.newTabSpec( "tag7" );
				spec.setContent( R.id.tab7 );
				spec.setIndicator( "7" );
				spec = tabs.newTabSpec( "tag8" );
				spec.setContent( R.id.tab8 );
				spec.setIndicator( "8" );

				Text1.setText( "В Java присутствуют переменные в которых может хранится информация разных типов \n String - хранит текст \n int - хранит и положительные и отрицательные целые числа \n float - хранит положительные и отрицательные не целыочисленные числа \n char - хранит какой либо символ \n boolean - хранит значение ктоорое может быть либо льжью либо правдой" );
				b1.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						tabs.setCurrentTab( 1 );
					}
				} );
				Drawable myDrawable = getResources().getDrawable( R.drawable.datatypes );
				img.setImageDrawable( myDrawable );

				Text2.setText( "Какие переменные используются для работы с числами" );
				cb1.setText( "String" );
				cb2.setText( "int" );
				cb3.setText( "float" );
				cb4.setText( "boolean" );

				b2.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String answerCb1 = dbAnswer.get( "cb1" ).toString();
						String answerCb2 = dbAnswer.get( "cb2" ).toString();
						String answerCb3 = dbAnswer.get( "cb3" ).toString();
						String answerCb4 = dbAnswer.get( "cb4" ).toString();

						String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
						Log.i( "answer sum", "" + answerSum );

						StringBuffer result = new StringBuffer();
						result.append( cb1.isChecked() );
						result.append( cb2.isChecked() );
						result.append( cb3.isChecked() );
						result.append( cb4.isChecked() );
						Log.i( "results", "" + result );

						if (answerSum.equals( result.toString() )) {
							tabs.setCurrentTab( 1 );
						}

					}
				} );

				Text4.setText( "Какие переменные используются для работы с символами" );
				cb5.setText( "String" );
				cb6.setText( "char" );
				cb7.setText( "float" );
				cb8.setText( "boolean" );

				b4.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String answerCb1 = dbAnswer.get( "cb5" ).toString();
						String answerCb2 = dbAnswer.get( "cb6" ).toString();
						String answerCb3 = dbAnswer.get( "cb7" ).toString();
						String answerCb4 = dbAnswer.get( "cb8" ).toString();

						String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
						Log.i( "answer sum", "" + answerSum );

						StringBuffer result = new StringBuffer();
						result.append( cb5.isChecked() );
						result.append( cb6.isChecked() );
						result.append( cb7.isChecked() );
						result.append( cb8.isChecked() );
						Log.i( "results", "" + result );

						if (answerSum.equals( result.toString() )) {
							Intent intent = new Intent( v.getContext(), MainMenuActivity.class );
							v.getContext().startActivity( intent );
						}

					}
				} );
			}
			if (quest == 2) {

				getResultFromDB( "6" );

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
				spec = tabs.newTabSpec( "tag4" );
				spec.setContent( R.id.tab4 );
				spec.setIndicator( "4" );
				spec = tabs.newTabSpec( "tag5" );
				spec.setContent( R.id.tab5 );
				spec.setIndicator( "5" );
				spec = tabs.newTabSpec( "tag6" );
				spec.setContent( R.id.tab6 );
				spec.setIndicator( "6" );
				spec = tabs.newTabSpec( "tag7" );
				spec.setContent( R.id.tab7 );
				spec.setIndicator( "7" );
				spec = tabs.newTabSpec( "tag8" );
				spec.setContent( R.id.tab8 );
				spec.setIndicator( "8" );

				Text1.setText( "Тем не менее, вы можете добавить final, ключевое слово, если вы не хотите, чтобы другие (или вы сами) перезаписывали существующие значения (это объявит переменную как final или constant, что означает неизменяемый и только для чтения):" );
				b1.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						tabs.setCurrentTab( 1 );
					}
				} );

				Text2.setText( "При каком условии надо использовать final или constant" );
				cb1.setText( "Если вы не хотите что бы это значение менялось" );
				cb2.setText( "Если вам надо что бы это значение завершало работу программы" );
				cb3.setText( "Если значение используется в последний раз" );
				cb4.setText( "Просто так" );

				b2.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String answerCb1 = dbAnswer.get( "cb1" ).toString();
						String answerCb2 = dbAnswer.get( "cb2" ).toString();
						String answerCb3 = dbAnswer.get( "cb3" ).toString();
						String answerCb4 = dbAnswer.get( "cb4" ).toString();

						String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
						Log.i( "answer sum", "" + answerSum );

						StringBuffer result = new StringBuffer();
						result.append( cb1.isChecked() );
						result.append( cb2.isChecked() );
						result.append( cb3.isChecked() );
						result.append( cb4.isChecked() );
						Log.i( "results", "" + result );

						if (answerSum.equals( result.toString() )) {
							Intent intent = new Intent( v.getContext(), MainMenuActivity.class );
							v.getContext().startActivity( intent );
						}

					}
				} );
			}
			if (quest == 3) {

				getResultFromDB( "7" );

				tabs.setup();
				TabHost.TabSpec spec = tabs.newTabSpec( "tag1" );
				spec.setContent( R.id.tab1 );
				spec.setIndicator( "1" );
				tabs.addTab( spec );
				spec = tabs.newTabSpec( "tag2" );
				spec.setContent( R.id.tab2 );
				spec.setIndicator( "2" );
				spec = tabs.newTabSpec( "tag3" );
				spec.setContent( R.id.tab3 );
				spec.setIndicator( "3" );
				tabs.addTab( spec );
				spec = tabs.newTabSpec( "tag4" );
				spec.setContent( R.id.tab4 );
				spec.setIndicator( "4" );
				spec = tabs.newTabSpec( "tag5" );
				spec.setContent( R.id.tab5 );
				spec.setIndicator( "5" );
				spec = tabs.newTabSpec( "tag6" );
				spec.setContent( R.id.tab6 );
				spec.setIndicator( "6" );
				spec = tabs.newTabSpec( "tag7" );
				spec.setContent( R.id.tab7 );
				spec.setIndicator( "7" );
				spec = tabs.newTabSpec( "tag8" );
				spec.setContent( R.id.tab8 );
				spec.setIndicator( "8" );

				Text1.setText( "Математические операторы токже часть программиорвания \n + - сложение \n - - вычитание \n * - умножение \n / - деление \n % - деление с остатком " );

				b1.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						tabs.setCurrentTab( 1 );
					}
				} );

				Text3.setText( "Часто использующиеся инкремент и декремент также важная часть, где ++ эквивалентен + 1 \n и так же с -- что будет эквивалентно - 1" );
				Drawable draw = getResources().getDrawable( R.drawable.icmadcm );
				img2.setImageDrawable( draw );

				b3.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent( v.getContext(), MainMenuActivity.class );
						v.getContext().startActivity( intent );
					}
				} );
			}
			if(quest == 4) {

				getResultFromDB( "8" );

				tabs.setup();
				TabHost.TabSpec spec = tabs.newTabSpec( "tag1" );
				spec.setContent( R.id.tab1 );
				spec.setIndicator( "1" );
				tabs.addTab( spec );
				spec = tabs.newTabSpec( "tag2" );
				spec.setContent( R.id.tab2 );
				spec.setIndicator( "3" );
				spec = tabs.newTabSpec( "tag3" );
				spec.setContent( R.id.tab3 );
				spec.setIndicator( "2" );
				tabs.addTab( spec );
				spec = tabs.newTabSpec( "tag4" );
				spec.setContent( R.id.tab4 );
				spec.setIndicator( "4" );
				spec = tabs.newTabSpec( "tag5" );
				spec.setContent( R.id.tab5 );
				spec.setIndicator( "5" );
				spec = tabs.newTabSpec( "tag6" );
				spec.setContent( R.id.tab6 );
				spec.setIndicator( "6" );
				spec = tabs.newTabSpec( "tag7" );
				spec.setContent( R.id.tab7 );
				spec.setIndicator( "7" );
				spec = tabs.newTabSpec( "tag8" );
				spec.setContent( R.id.tab8 );
				spec.setIndicator( "8" );

				Text1.setText( "Существуют так де операторы сравнение которые сравнивают значение и говорят ложное оно или истинное. == - сравнивает равно ли решение \n != - сравнивает не равно ли решение \n < ; > - сравнивает больше или меньше \n <= ; >= - сравнивает больше или равно ,или меньше или равно  " );
				b1.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						tabs.setCurrentTab( 1 );
					}
				} );

				Text3.setText( "Для сравнивания более 2 аргументов можно использовать: \n&& - результат будет истиной если 2 других значения так же истина \n|| - результат будет истиной если 1 из 2 значений так же будет истиной" );
				b3.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent( v.getContext(), MainMenuActivity.class );
						v.getContext().startActivity( intent );
					}
				} );
			}
		}
		if(theme == 3) {
			if (quest == 1) {
				getResultFromDB( "1" );

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
				spec = tabs.newTabSpec( "tag4" );
				spec.setContent( R.id.tab4 );
				spec.setIndicator( "4" );
				spec = tabs.newTabSpec( "tag5" );
				spec.setContent( R.id.tab5 );
				spec.setIndicator( "5" );
				spec = tabs.newTabSpec( "tag6" );
				spec.setContent( R.id.tab6 );
				spec.setIndicator( "6" );
				spec = tabs.newTabSpec( "tag7" );
				spec.setContent( R.id.tab7 );
				spec.setIndicator( "7" );
				spec = tabs.newTabSpec( "tag8" );
				spec.setContent( R.id.tab8 );
				spec.setIndicator( "8" );

				Text1.setText( "Java представляет собой язык программирования и платформу вычислений. " +
						"Существует множество приложений и веб-сайтов, которые не работают при отсутствии установленной Java, и с каждым днем число таких веб-сайтов и приложений увеличивается." +
						"Java отличается быстротой, высоким уровнем защиты и надежностью. " +
						"От портативных компьютеров до центров данных, от игровых консолей до суперкомпьютеров, используемых для научных разработок, от сотовых телефонов до сети Интернет — Java повсюду! " +
						"Java можно загрузить бесплатно." +
						"Если вы разрабатываете встроенное или бытовое устройство и хотите использовать в нем технологии Java, свяжитесь со специалистами Oracle и получите подробную информацию об интеграции Java в различные типы устройств." );
				b1.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						tabs.setCurrentTab( 1 );
					}
				} );

				Text2.setText( "Как можно описать java?" );
				cb1.setText( "Бесплатный и строго типизированный объектно-ориентированный язык программирования, предназначенный для множества устройств" );
				cb2.setText( "Язык программирования для суперкомпютеров" );
				cb3.setText( "Проффесиональный редактор текста" );
				cb4.setText( "Платная программа для программирования" );

				b2.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String answerCb1 = dbAnswer.get( "cb1" ).toString();
						String answerCb2 = dbAnswer.get( "cb2" ).toString();
						String answerCb3 = dbAnswer.get( "cb3" ).toString();
						String answerCb4 = dbAnswer.get( "cb4" ).toString();

						String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
						Log.i( "answer sum", "" + answerSum );

						StringBuffer result = new StringBuffer();
						result.append( cb1.isChecked() );
						result.append( cb2.isChecked() );
						result.append( cb3.isChecked() );
						result.append( cb4.isChecked() );
						Log.i( "results", "" + result );

						if (answerSum.equals( result.toString() )) {
							Intent intent = new Intent( v.getContext(), MainMenuActivity.class );
							v.getContext().startActivity( intent );
						}

					}
				} );
			}
			if (quest == 2) {

				getResultFromDB( "2" );

				tabs.setup();
				TabHost.TabSpec spec = tabs.newTabSpec( "tag1" );
				spec.setContent( R.id.tab1 );
				spec.setIndicator( "1" );
				tabs.addTab( spec );
				spec = tabs.newTabSpec( "tag2" );
				spec.setContent( R.id.tab2 );
				spec.setIndicator( "8" );
				spec = tabs.newTabSpec( "tag3" );
				spec.setContent( R.id.tab3 );
				spec.setIndicator( "3" );
				spec = tabs.newTabSpec( "tag4" );
				spec.setContent( R.id.tab4 );
				spec.setIndicator( "4" );
				spec = tabs.newTabSpec( "tag5" );
				spec.setContent( R.id.tab5 );
				spec.setIndicator( "5" );
				spec = tabs.newTabSpec( "tag6" );
				spec.setContent( R.id.tab6 );
				spec.setIndicator( "6" );
				spec = tabs.newTabSpec( "tag7" );
				spec.setContent( R.id.tab7 );
				spec.setIndicator( "7" );
				spec = tabs.newTabSpec( "tag8" );
				spec.setContent( R.id.tab8 );
				spec.setIndicator( "2" );
				tabs.addTab( spec );

				Text1.setText( "Каждая программа нуждается в объекте - что либо, обладающее определённым состоянием и поведением, имеющая определенные свойства (атрибуты) и операции над ними (методы)" +
						"Класс — может быть определен как шаблон, который описывает поведение объекта. Метод — является в основном поведением. Класс может содержать несколько методов. Именно в методах логически записанные данные манипулируют и выполняют все действия." );

				Drawable myDrawable = getResources().getDrawable( R.drawable.class_1_2_tak );
				img.setImageDrawable( myDrawable );

				b1.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						tabs.setCurrentTab( 1 );
					}
				} );
				Text7.setText( "Как можно описать объект в java?" );
				rb1.setText( "Человек какой либо професии" );
				rb2.setText( "Не живой объект который существует" );
				rb3.setText( "Что либо обладающее свойствами, шаблонами и поведением" );

				b2.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String answerRb1 = dbAnswer.get( "rb1" ).toString();
						String answerRb2 = dbAnswer.get( "rb2" ).toString();
						String answerRb3 = dbAnswer.get( "rb3" ).toString();


						String answerSum = answerRb1 + answerRb2 + answerRb3;
						Log.i( "answer sum", "" + answerSum );

						StringBuffer result = new StringBuffer();
						result.append( rb1.isEnabled() );
						result.append( rb2.isEnabled() );
						result.append( rb3.isEnabled() );
						Log.i( "results", "" + result );

						if (answerSum.equals( result.toString() )) {
							Intent intent = new Intent( v.getContext(), MainMenuActivity.class );
							v.getContext().startActivity( intent );
						}

					}
				} );
			}
			if (quest == 3) {

				getResultFromDB( "3" );

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
				spec.setIndicator( "4" );
				spec = tabs.newTabSpec( "tag4" );
				spec.setContent( R.id.tab4 );
				spec.setIndicator( "3" );
				tabs.addTab( spec );
				spec = tabs.newTabSpec( "tag5" );
				spec.setContent( R.id.tab5 );
				spec.setIndicator( "5" );
				spec = tabs.newTabSpec( "tag6" );
				spec.setContent( R.id.tab6 );
				spec.setIndicator( "6" );
				spec = tabs.newTabSpec( "tag7" );
				spec.setContent( R.id.tab7 );
				spec.setIndicator( "7" );
				spec = tabs.newTabSpec( "tag8" );
				spec.setContent( R.id.tab8 );
				spec.setIndicator( "8" );

				Text1.setText( "Первая программа - то с чего начинается всё! Для каждого 1 программа одинакова - Привет мир!" +
						"для запуска программны мы должны создать метод main , далее создается тело кода с выводом текста по виду оно должно выглядеть примерно так" );

				Drawable myDrawable = getResources().getDrawable( R.drawable.code1 );
				img.setImageDrawable( myDrawable );

				b1.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						tabs.setCurrentTab( 1 );
					}
				} );

				Text2.setText( "Что выведет предыдущий код?" );
				cb1.setText( "Hello world!" );
				cb2.setText( "error" );
				cb3.setText( "Hello\nworld\n!" );
				cb4.setText( "Hello world! в скобках" );

				b2.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String answerCb1 = dbAnswer.get( "cb1" ).toString();
						String answerCb2 = dbAnswer.get( "cb2" ).toString();
						String answerCb3 = dbAnswer.get( "cb3" ).toString();
						String answerCb4 = dbAnswer.get( "cb4" ).toString();

						String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
						Log.i( "answer sum", "" + answerSum );

						StringBuffer result = new StringBuffer();
						result.append( cb1.isChecked() );
						result.append( cb2.isChecked() );
						result.append( cb3.isChecked() );
						result.append( cb4.isChecked() );
						Log.i( "results", "" + result );

						if (answerSum.equals( result.toString() )) {
							Intent intent = new Intent( v.getContext(), MainMenuActivity.class );
							v.getContext().startActivity( intent );
						}
					}
				} );
			}
			if(quest == 4) {

				getResultFromDB( "4" );

				tabs.setup();
				TabHost.TabSpec spec = tabs.newTabSpec( "tag1" );
				spec.setContent( R.id.tab1 );
				spec.setIndicator( "3" );
				spec = tabs.newTabSpec( "tag2" );
				spec.setContent( R.id.tab2 );
				spec.setIndicator( "1" );
				tabs.addTab( spec );
				spec = tabs.newTabSpec( "tag3" );
				spec.setContent( R.id.tab3 );
				spec.setIndicator( "4" );
				spec = tabs.newTabSpec( "tag4" );
				spec.setContent( R.id.tab4 );
				spec.setIndicator( "2" );
				tabs.addTab( spec );
				spec = tabs.newTabSpec( "tag5" );
				spec.setContent( R.id.tab5 );
				spec.setIndicator( "5" );
				spec = tabs.newTabSpec( "tag6" );
				spec.setContent( R.id.tab6 );
				spec.setIndicator( "6" );
				spec = tabs.newTabSpec( "tag7" );
				spec.setContent( R.id.tab7 );
				spec.setIndicator( "7" );
				spec = tabs.newTabSpec( "tag8" );
				spec.setContent( R.id.tab8 );
				spec.setIndicator( "8" );

				Text2.setText( "Что бы вывести что то на экран используется команда" );
				cb1.setText( "std::cout<<" );
				cb2.setText( "println" );
				cb3.setText( "System.out.println()" );
				cb4.setText( "print" );

				b2.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String answerCb1 = dbAnswer.get( "cb1" ).toString();
						String answerCb2 = dbAnswer.get( "cb2" ).toString();
						String answerCb3 = dbAnswer.get( "cb3" ).toString();
						String answerCb4 = dbAnswer.get( "cb4" ).toString();

						String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
						Log.i( "answer sum", "" + answerSum );

						StringBuffer result = new StringBuffer();
						result.append( cb1.isChecked() );
						result.append( cb2.isChecked() );
						result.append( cb3.isChecked() );
						result.append( cb4.isChecked() );
						Log.i( "results", "" + result );

						if (answerSum.equals( result.toString() )) {
							tabs.setCurrentTab( 1 );
						}
					}
				} );

				Text4.setText( "Какой мнетод обязателен в любой программе" );
				cb5.setText( "void" );
				cb6.setText( "return" );
				cb7.setText( "System." );
				cb8.setText( "main" );

				b4.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String answerCb1 = dbAnswer.get( "cb5" ).toString();
						String answerCb2 = dbAnswer.get( "cb6" ).toString();
						String answerCb3 = dbAnswer.get( "cb7" ).toString();
						String answerCb4 = dbAnswer.get( "cb8" ).toString();

						String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
						Log.i( "answer sum", "" + answerSum );

						StringBuffer result = new StringBuffer();
						result.append( cb5.isChecked() );
						result.append( cb6.isChecked() );
						result.append( cb7.isChecked() );
						result.append( cb8.isChecked() );
						Log.i( "results", "" + result );

						if (answerSum.equals( result.toString() )) {
							Intent intent = new Intent( v.getContext(), MainMenuActivity.class );
							v.getContext().startActivity( intent );
						}
					}
				} );
			}
			}
			if (theme == 4) {
				if (quest == 1) {
					getResultFromDB( "1" );

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
					spec = tabs.newTabSpec( "tag4" );
					spec.setContent( R.id.tab4 );
					spec.setIndicator( "4" );
					spec = tabs.newTabSpec( "tag5" );
					spec.setContent( R.id.tab5 );
					spec.setIndicator( "5" );
					spec = tabs.newTabSpec( "tag6" );
					spec.setContent( R.id.tab6 );
					spec.setIndicator( "6" );
					spec = tabs.newTabSpec( "tag7" );
					spec.setContent( R.id.tab7 );
					spec.setIndicator( "7" );
					spec = tabs.newTabSpec( "tag8" );
					spec.setContent( R.id.tab8 );
					spec.setIndicator( "8" );

					Text1.setText( "Java представляет собой язык программирования и платформу вычислений. " +
							"Существует множество приложений и веб-сайтов, которые не работают при отсутствии установленной Java, и с каждым днем число таких веб-сайтов и приложений увеличивается." +
							"Java отличается быстротой, высоким уровнем защиты и надежностью. " +
							"От портативных компьютеров до центров данных, от игровых консолей до суперкомпьютеров, используемых для научных разработок, от сотовых телефонов до сети Интернет — Java повсюду! " +
							"Java можно загрузить бесплатно." +
							"Если вы разрабатываете встроенное или бытовое устройство и хотите использовать в нем технологии Java, свяжитесь со специалистами Oracle и получите подробную информацию об интеграции Java в различные типы устройств." );
					b1.setOnClickListener( new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							tabs.setCurrentTab( 1 );
						}
					} );

					Text2.setText( "Как можно описать java?" );
					cb1.setText( "Бесплатный и строго типизированный объектно-ориентированный язык программирования, предназначенный для множества устройств" );
					cb2.setText( "Язык программирования для суперкомпютеров" );
					cb3.setText( "Проффесиональный редактор текста" );
					cb4.setText( "Платная программа для программирования" );

					b2.setOnClickListener( new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							String answerCb1 = dbAnswer.get( "cb1" ).toString();
							String answerCb2 = dbAnswer.get( "cb2" ).toString();
							String answerCb3 = dbAnswer.get( "cb3" ).toString();
							String answerCb4 = dbAnswer.get( "cb4" ).toString();

							String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
							Log.i( "answer sum", "" + answerSum );

							StringBuffer result = new StringBuffer();
							result.append( cb1.isChecked() );
							result.append( cb2.isChecked() );
							result.append( cb3.isChecked() );
							result.append( cb4.isChecked() );
							Log.i( "results", "" + result );

							if (answerSum.equals( result.toString() )) {
								Intent intent = new Intent( v.getContext(), MainMenuActivity.class );
								v.getContext().startActivity( intent );
							}

						}
					} );
				}
				if (quest == 2) {

					getResultFromDB( "2" );

					tabs.setup();
					TabHost.TabSpec spec = tabs.newTabSpec( "tag1" );
					spec.setContent( R.id.tab1 );
					spec.setIndicator( "1" );
					tabs.addTab( spec );
					spec = tabs.newTabSpec( "tag2" );
					spec.setContent( R.id.tab2 );
					spec.setIndicator( "8" );
					spec = tabs.newTabSpec( "tag3" );
					spec.setContent( R.id.tab3 );
					spec.setIndicator( "3" );
					spec = tabs.newTabSpec( "tag4" );
					spec.setContent( R.id.tab4 );
					spec.setIndicator( "4" );
					spec = tabs.newTabSpec( "tag5" );
					spec.setContent( R.id.tab5 );
					spec.setIndicator( "5" );
					spec = tabs.newTabSpec( "tag6" );
					spec.setContent( R.id.tab6 );
					spec.setIndicator( "6" );
					spec = tabs.newTabSpec( "tag7" );
					spec.setContent( R.id.tab7 );
					spec.setIndicator( "7" );
					spec = tabs.newTabSpec( "tag8" );
					spec.setContent( R.id.tab8 );
					spec.setIndicator( "2" );
					tabs.addTab( spec );

					Text1.setText( "Каждая программа нуждается в объекте - что либо, обладающее определённым состоянием и поведением, имеющая определенные свойства (атрибуты) и операции над ними (методы)" +
							"Класс — может быть определен как шаблон, который описывает поведение объекта. Метод — является в основном поведением. Класс может содержать несколько методов. Именно в методах логически записанные данные манипулируют и выполняют все действия." );

					Drawable myDrawable = getResources().getDrawable( R.drawable.class_1_2_tak );
					img.setImageDrawable( myDrawable );

					b1.setOnClickListener( new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							tabs.setCurrentTab( 1 );
						}
					} );
					Text7.setText( "Как можно описать объект в java?" );
					rb1.setText( "Человек какой либо професии" );
					rb2.setText( "Не живой объект который существует" );
					rb3.setText( "Что либо обладающее свойствами, шаблонами и поведением" );

					b2.setOnClickListener( new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							String answerRb1 = dbAnswer.get( "rb1" ).toString();
							String answerRb2 = dbAnswer.get( "rb2" ).toString();
							String answerRb3 = dbAnswer.get( "rb3" ).toString();


							String answerSum = answerRb1 + answerRb2 + answerRb3;
							Log.i( "answer sum", "" + answerSum );

							StringBuffer result = new StringBuffer();
							result.append( rb1.isEnabled() );
							result.append( rb2.isEnabled() );
							result.append( rb3.isEnabled() );
							Log.i( "results", "" + result );

							if (answerSum.equals( result.toString() )) {
								Intent intent = new Intent( v.getContext(), MainMenuActivity.class );
								v.getContext().startActivity( intent );
							}

						}
					} );
				}
				if (quest == 3) {

					getResultFromDB( "3" );

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
					spec.setIndicator( "4" );
					spec = tabs.newTabSpec( "tag4" );
					spec.setContent( R.id.tab4 );
					spec.setIndicator( "3" );
					tabs.addTab( spec );
					spec = tabs.newTabSpec( "tag5" );
					spec.setContent( R.id.tab5 );
					spec.setIndicator( "5" );
					spec = tabs.newTabSpec( "tag6" );
					spec.setContent( R.id.tab6 );
					spec.setIndicator( "6" );
					spec = tabs.newTabSpec( "tag7" );
					spec.setContent( R.id.tab7 );
					spec.setIndicator( "7" );
					spec = tabs.newTabSpec( "tag8" );
					spec.setContent( R.id.tab8 );
					spec.setIndicator( "8" );

					Text1.setText( "Первая программа - то с чего начинается всё! Для каждого 1 программа одинакова - Привет мир!" +
							"для запуска программны мы должны создать метод main , далее создается тело кода с выводом текста по виду оно должно выглядеть примерно так" );

					Drawable myDrawable = getResources().getDrawable( R.drawable.code1 );
					img.setImageDrawable( myDrawable );

					b1.setOnClickListener( new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							tabs.setCurrentTab( 1 );
						}
					} );

					Text2.setText( "Что выведет предыдущий код?" );
					cb1.setText( "Hello world!" );
					cb2.setText( "error" );
					cb3.setText( "Hello\nworld\n!" );
					cb4.setText( "Hello world! в скобках" );

					b2.setOnClickListener( new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							String answerCb1 = dbAnswer.get( "cb1" ).toString();
							String answerCb2 = dbAnswer.get( "cb2" ).toString();
							String answerCb3 = dbAnswer.get( "cb3" ).toString();
							String answerCb4 = dbAnswer.get( "cb4" ).toString();

							String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
							Log.i( "answer sum", "" + answerSum );

							StringBuffer result = new StringBuffer();
							result.append( cb1.isChecked() );
							result.append( cb2.isChecked() );
							result.append( cb3.isChecked() );
							result.append( cb4.isChecked() );
							Log.i( "results", "" + result );

							if (answerSum.equals( result.toString() )) {
								Intent intent = new Intent( v.getContext(), MainMenuActivity.class );
								v.getContext().startActivity( intent );
							}
						}
					} );
				}
				if (quest == 4) {

					getResultFromDB( "4" );

					tabs.setup();
					TabHost.TabSpec spec = tabs.newTabSpec( "tag1" );
					spec.setContent( R.id.tab1 );
					spec.setIndicator( "3" );
					spec = tabs.newTabSpec( "tag2" );
					spec.setContent( R.id.tab2 );
					spec.setIndicator( "1" );
					tabs.addTab( spec );
					spec = tabs.newTabSpec( "tag3" );
					spec.setContent( R.id.tab3 );
					spec.setIndicator( "4" );
					spec = tabs.newTabSpec( "tag4" );
					spec.setContent( R.id.tab4 );
					spec.setIndicator( "2" );
					tabs.addTab( spec );
					spec = tabs.newTabSpec( "tag5" );
					spec.setContent( R.id.tab5 );
					spec.setIndicator( "5" );
					spec = tabs.newTabSpec( "tag6" );
					spec.setContent( R.id.tab6 );
					spec.setIndicator( "6" );
					spec = tabs.newTabSpec( "tag7" );
					spec.setContent( R.id.tab7 );
					spec.setIndicator( "7" );
					spec = tabs.newTabSpec( "tag8" );
					spec.setContent( R.id.tab8 );
					spec.setIndicator( "8" );

					Text2.setText( "Что бы вывести что то на экран используется команда" );
					cb1.setText( "std::cout<<" );
					cb2.setText( "println" );
					cb3.setText( "System.out.println()" );
					cb4.setText( "print" );

					b2.setOnClickListener( new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							String answerCb1 = dbAnswer.get( "cb1" ).toString();
							String answerCb2 = dbAnswer.get( "cb2" ).toString();
							String answerCb3 = dbAnswer.get( "cb3" ).toString();
							String answerCb4 = dbAnswer.get( "cb4" ).toString();

							String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
							Log.i( "answer sum", "" + answerSum );

							StringBuffer result = new StringBuffer();
							result.append( cb1.isChecked() );
							result.append( cb2.isChecked() );
							result.append( cb3.isChecked() );
							result.append( cb4.isChecked() );
							Log.i( "results", "" + result );

							if (answerSum.equals( result.toString() )) {
								tabs.setCurrentTab( 1 );
							}
						}
					} );

					Text4.setText( "Какой мнетод обязателен в любой программе" );
					cb5.setText( "void" );
					cb6.setText( "return" );
					cb7.setText( "System." );
					cb8.setText( "main" );

					b4.setOnClickListener( new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							String answerCb1 = dbAnswer.get( "cb5" ).toString();
							String answerCb2 = dbAnswer.get( "cb6" ).toString();
							String answerCb3 = dbAnswer.get( "cb7" ).toString();
							String answerCb4 = dbAnswer.get( "cb8" ).toString();

							String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
							Log.i( "answer sum", "" + answerSum );

							StringBuffer result = new StringBuffer();
							result.append( cb5.isChecked() );
							result.append( cb6.isChecked() );
							result.append( cb7.isChecked() );
							result.append( cb8.isChecked() );
							Log.i( "results", "" + result );

							if (answerSum.equals( result.toString() )) {
								Intent intent = new Intent( v.getContext(), MainMenuActivity.class );
								v.getContext().startActivity( intent );
							}
						}
					} );
				}
			}
	}

    private void getResultFromDB(String Document){
	    DocumentReference docRef = db.collection("Quests").document(Document);
	    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
		    @Override
		    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
			    if (task.isSuccessful()) {
				    DocumentSnapshot document = task.getResult();
				    if (document.exists()) {
					    dbAnswer = document.getData();
				    }
			    } else {
				    Log.d("Something went wrong", "get failed with ", task.getException());
			    }
		    }
	    });

    }
}