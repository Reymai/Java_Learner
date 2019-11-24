package com.example.javalearner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ForgottenPasswordActivity extends AppCompatActivity {

	EditText mEmailEdit;
	Button mRestorePasswordBtn;
	TextView mBackFromRecover;

	private FirebaseAuth mAuth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgotten_password);

		mEmailEdit = findViewById(R.id.emailEdit);
		mRestorePasswordBtn = findViewById(R.id.restorePasswordBtn);
		mBackFromRecover = findViewById(R.id.BackFromRecover);


		mRestorePasswordBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String email = mEmailEdit.getText().toString();

				boolean InvalidEmailPatterns = !Patterns.EMAIL_ADDRESS.matcher(email).matches();

				if (InvalidEmailPatterns) {
					mEmailEdit.setFocusable(true);
					mEmailEdit.setError("Invalid email address");
				}else{
					restorePassword(email);
				}
			}
		});

		mBackFromRecover.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(ForgottenPasswordActivity.this, LoginActivity.class));
			}
		});

	}

	private void restorePassword(String email) {

		mAuth = FirebaseAuth.getInstance();

		mAuth.sendPasswordResetEmail(email)
				.addOnCompleteListener(new OnCompleteListener<Void>() {
					@Override
					public void onComplete(@NonNull Task<Void> task) {
						if (task.isSuccessful()) {
							Toast.makeText(ForgottenPasswordActivity.this, "Email sent", Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(ForgottenPasswordActivity.this, "Email not sent", Toast.LENGTH_SHORT).show();
						}
					}
				}).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure(@NonNull Exception e) {
				Toast.makeText(ForgottenPasswordActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
			}
		});
	}
}
