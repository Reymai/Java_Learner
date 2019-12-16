package com.example.javalearner;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TabHost;
import android.widget.TextView;

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
    TextView Text1, Text2, Text3, Text4, Text5;
    CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10,
		    cb11,cb12, cb13, cb14, cb15, cb16, cb17, cb18, cb19, cb20;
    Button b1, b2, b3, b4, b5;
	private static Map dbAnswer;

	private static FirebaseFirestore db = FirebaseFirestore.getInstance();

	@Override
    protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quest_actiity);

		b1 = findViewById(R.id.btn);
		b2 = findViewById(R.id.btn1);
		b3 = findViewById(R.id.btn2);
		b4 = findViewById(R.id.btn3);
		b5 = findViewById(R.id.btn4);

		cb5 = findViewById(R.id.a4);
		cb6 = findViewById(R.id.a5);
		cb7 = findViewById(R.id.a6);
		cb8 = findViewById(R.id.a7);
		cb9 = findViewById(R.id.a8);
		cb10 = findViewById(R.id.a9);
		cb11 = findViewById(R.id.a10);
		cb12 = findViewById(R.id.a11);
		cb13 = findViewById(R.id.a12);
		cb14 = findViewById(R.id.a13);
		cb15 = findViewById(R.id.a14);
		cb16 = findViewById(R.id.a15);
		cb17 = findViewById(R.id.a16);
		cb18 = findViewById(R.id.a17);
		cb19 = findViewById(R.id.a18);
		cb20 = findViewById(R.id.a19);

		Text1 = findViewById(R.id.txt);
		Text2 = findViewById(R.id.txt1);
		Text3 = findViewById(R.id.txt2);
		Text4 = findViewById(R.id.txt3);
		Text5 = findViewById(R.id.txt4);

		mTabHost = findViewById(R.id.TaskTab);
		final TabHost tabs = findViewById(R.id.TaskTab);

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


		int quest1 = getIntent().getIntExtra("1", 0);
		int quest2 = getIntent().getIntExtra("2", 0);
		int quest3 = getIntent().getIntExtra("3", 0);
		int quest4 = getIntent().getIntExtra("4", 0);


		if (quest1 == 1) {

			getResultFromDB("1");

			Text1.setText("All java programms need to have a main class");

			b1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					tabs.setCurrentTab(1);
				}
			});
			Text2.setText("What any java programm need");
			cb5.setText("return");
			cb6.setText("class");
			cb7.setText("class main");
			cb8.setText("nothing");


			b2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String answerCb1 = dbAnswer.get("cb1").toString();
					String answerCb2 = dbAnswer.get("cb2").toString();
					String answerCb3 = dbAnswer.get("cb3").toString();
					String answerCb4 = dbAnswer.get("cb4").toString();

					String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
					Log.i("answer sum", "" + answerSum);

					StringBuffer result = new StringBuffer();
					result.append(cb5.isChecked());
					result.append(cb6.isChecked());
					result.append(cb7.isChecked());
					result.append(cb8.isChecked());
					Log.i("results", "" + result);

					if (answerSum.equals(result.toString())) {
						tabs.setCurrentTab(2);
					}

				}
			});
			Text3.setText("To print something in java use System.out.println(text); ");
			b3.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String answerCb1 = dbAnswer.get("cb5").toString();
					String answerCb2 = dbAnswer.get("cb6").toString();
					String answerCb3 = dbAnswer.get("cb7").toString();
					String answerCb4 = dbAnswer.get("cb8").toString();

					String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
					Log.i("answer sum", "" + answerSum);

					StringBuffer result = new StringBuffer();
					result.append(cb9.isChecked());
					result.append(cb10.isChecked());
					result.append(cb11.isChecked());
					result.append(cb12.isChecked());
					Log.i("results", "" + result);

					if (answerSum.equals(result.toString())) {
						tabs.setCurrentTab(3);
					}
				}
			});
			Text4.setText("What command uses to print something");
			cb9.setText("System.out.println");
			cb10.setText("std::cout<<");
			cb11.setText("vivod");
			cb12.setText("println");
			b4.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					tabs.setCurrentTab(5);
				}
			});
		}
		if (quest1 == 1) {

			getResultFromDB("1");

			Text1.setText("All java programms need to have a main class");

			b1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					tabs.setCurrentTab(1);
				}
			});
			Text2.setText("What any java programm need");
			cb5.setText("return");
			cb6.setText("class");
			cb7.setText("class main");
			cb8.setText("nothing");


			b2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String answerCb1 = dbAnswer.get("cb1").toString();
					String answerCb2 = dbAnswer.get("cb2").toString();
					String answerCb3 = dbAnswer.get("cb3").toString();
					String answerCb4 = dbAnswer.get("cb4").toString();

					String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
					Log.i("answer sum", "" + answerSum);

					StringBuffer result = new StringBuffer();
					result.append(cb5.isChecked());
					result.append(cb6.isChecked());
					result.append(cb7.isChecked());
					result.append(cb8.isChecked());
					Log.i("results", "" + result);

					if (answerSum.equals(result.toString())) {
						tabs.setCurrentTab(2);
					}

				}
			});
			Text3.setText("To print something in java use System.out.println(text); ");
			b3.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String answerCb1 = dbAnswer.get("cb5").toString();
					String answerCb2 = dbAnswer.get("cb6").toString();
					String answerCb3 = dbAnswer.get("cb7").toString();
					String answerCb4 = dbAnswer.get("cb8").toString();

					String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
					Log.i("answer sum", "" + answerSum);

					StringBuffer result = new StringBuffer();
					result.append(cb9.isChecked());
					result.append(cb10.isChecked());
					result.append(cb11.isChecked());
					result.append(cb12.isChecked());
					Log.i("results", "" + result);

					if (answerSum.equals(result.toString())) {
						tabs.setCurrentTab(3);
					}
				}
			});
			Text4.setText("What command uses to print something");
			cb9.setText("System.out.println");
			cb10.setText("std::cout<<");
			cb11.setText("vivod");
			cb12.setText("println");
			b4.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					tabs.setCurrentTab(5);
				}
			});
		}
		if (quest2 == 2) {
			getResultFromDB("2");

			b1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					tabs.setCurrentTab(1);
				}
			});
			Text2.setText("What command uses to print something");
			cb5.setText("System.out.println");
			cb6.setText("std::cout<<");
			cb7.setText("vivod");
			cb8.setText("println");

			b2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String answerCb1 = dbAnswer.get("cb1").toString();
					String answerCb2 = dbAnswer.get("cb2").toString();
					String answerCb3 = dbAnswer.get("cb3").toString();
					String answerCb4 = dbAnswer.get("cb4").toString();

					String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
					Log.i("answer sum", "" + answerSum);

					StringBuffer result = new StringBuffer();
					result.append(cb5.isChecked());
					result.append(cb6.isChecked());
					result.append(cb7.isChecked());
					result.append(cb8.isChecked());
					Log.i("results", "" + result);

					if (answerSum.equals(result.toString())) {
						tabs.setCurrentTab(2);
					}
				}
			});
		}
		Text3.setText("Java have some data types, for numbers uses int, double, float , for text String , for characters uses char , for true or false uses bolean");
//            cb5.setText( "System.out.println" );
//            cb6.setText( "std::cout<<" );
//            cb7.setText( "vivod" );
//            cb8.setText( "println" );

		b2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String answerCb1 = dbAnswer.get("cb5").toString();
				String answerCb2 = dbAnswer.get("cb6").toString();
				String answerCb3 = dbAnswer.get("cb7").toString();
				String answerCb4 = dbAnswer.get("cb8").toString();

				String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
				Log.i("answer sum", "" + answerSum);

				StringBuffer result = new StringBuffer();
				result.append(cb9.isChecked());
				result.append(cb10.isChecked());
				result.append(cb11.isChecked());
				result.append(cb12.isChecked());
				Log.i("results", "" + result);

				if (answerSum.equals(result.toString())) {
					tabs.setCurrentTab(2);
				}
			}
		});
		if (quest2 == 3) {
			getResultFromDB("3");

			b1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					tabs.setCurrentTab(1);
				}
			});
			Text2.setText("Java uses some primitive operators like: + - plus ; - -minus ; * multiplication ; / - division ; % - division with modulo?");
//			cb5.setText( "System.out.println" );
//			cb6.setText( "std::cout<<" );
//			cb7.setText( "vivod" );
//			cb8.setText( "println" );

			b2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String answerCb1 = dbAnswer.get("cb1").toString();
					String answerCb2 = dbAnswer.get("cb2").toString();
					String answerCb3 = dbAnswer.get("cb3").toString();
					String answerCb4 = dbAnswer.get("cb4").toString();

					String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
					Log.i("answer sum", "" + answerSum);

					StringBuffer result = new StringBuffer();
					result.append(cb5.isChecked());
					result.append(cb6.isChecked());
					result.append(cb7.isChecked());
					result.append(cb8.isChecked());
					Log.i("results", "" + result);

					if (answerSum.equals(result.toString())) {
						tabs.setCurrentTab(2);
					}
				}
			});
			if (quest2 == 4) {
				getResultFromDB("4");

				b1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						tabs.setCurrentTab(1);
					}
				});
				Text2.setText("String is a sequence of characters");
//			cb5.setText( "System.out.println" );
//			cb6.setText( "std::cout<<" );
//			cb7.setText( "vivod" );
//			cb8.setText( "println" );

				b2.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						String answerCb1 = dbAnswer.get("cb1").toString();
						String answerCb2 = dbAnswer.get("cb2").toString();
						String answerCb3 = dbAnswer.get("cb3").toString();
						String answerCb4 = dbAnswer.get("cb4").toString();

						String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
						Log.i("answer sum", "" + answerSum);

						StringBuffer result = new StringBuffer();
						result.append(cb5.isChecked());
						result.append(cb6.isChecked());
						result.append(cb7.isChecked());
						result.append(cb8.isChecked());
						Log.i("results", "" + result);

						if (answerSum.equals(result.toString())) {
							tabs.setCurrentTab(2);
						}
					}
				});
			}
			Text3.setText("How much characters can be in string ");
//		cb5.setText( "System.out.println" );
//		cb6.setText( "std::cout<<" );
//		cb7.setText( "vivod" );
//		cb8.setText( "println" );

			b2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String answerCb1 = dbAnswer.get("cb5").toString();
					String answerCb2 = dbAnswer.get("cb6").toString();
					String answerCb3 = dbAnswer.get("cb7").toString();
					String answerCb4 = dbAnswer.get("cb8").toString();

					String answerSum = answerCb1 + answerCb2 + answerCb3 + answerCb4;
					Log.i("answer sum", "" + answerSum);

					StringBuffer result = new StringBuffer();
					result.append(cb9.isChecked());
					result.append(cb10.isChecked());
					result.append(cb11.isChecked());
					result.append(cb12.isChecked());
					Log.i("results", "" + result);

					if (answerSum.equals(result.toString())) {
						tabs.setCurrentTab(2);
					}
				}
			});
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