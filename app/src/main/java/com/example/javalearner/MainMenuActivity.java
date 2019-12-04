package com.example.javalearner;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Locale;

public class MainMenuActivity extends AppCompatActivity {

    ImageButton mLogoutBtn;
    ImageButton mSettingsBtn;
    ImageButton mLibraryBtn;
    ImageButton mProfileBtn;
    TextView mUsername;
    TextView mLVLTxt;
    ProgressBar mProgressBar;
    ImageView mProfileImg;

    Uri mPrifileUri;

    private static final int PICK_IMAGE_REQUEST = 1;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;


	@SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mLogoutBtn = findViewById(R.id.LogoutBtn);
        mUsername = findViewById(R.id.Username);
        mProgressBar = findViewById(R.id.progressBar);
        mLVLTxt = findViewById(R.id.LVLTxt);
        mSettingsBtn = findViewById(R.id.SettingsBtn);
        mProfileImg = findViewById(R.id.ProfileImg);
        mLibraryBtn = findViewById(R.id.LibraryBtn);
        mProfileBtn = findViewById(R.id.ProfileBtn);

        mStorageRef = FirebaseStorage.getInstance().getReference("Avatars");
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Avatars");
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("Avatars");


        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainMenuActivity.this, "You have been logout!!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainMenuActivity.this, LoginActivity.class));
            }

        });

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            mUsername.setText(name);
        }

        //Language
        SharedPreferences sharedPref = MainMenuActivity.this.getPreferences(Context.MODE_PRIVATE);
        String dbLang = DatabaseHelper.DatabaseRead("users", user.getEmail(), "Language");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Locale locale = new Locale(dbLang);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Locale", locale.getLanguage());
        Log.e("Locale","Changed to: "+locale.getLanguage());
        editor.apply();

        if (!LocaleHelper.checkLocaleSharedPreferences(user, "Locale", sharedPref, this)) {
            super.recreate();
        } else{
            String SPlocale = getResources().getConfiguration().locale.getLanguage();
            Log.e("Configuration locale", "is " +SPlocale);
            String language = sharedPref.getString("Locale", null);

            if (!SPlocale.equals(language)){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LocaleHelper.changeLanguage(SPlocale, this);
                super.recreate();
            }
        }

        //Progress

		mProgressBar.setProgress(0);
		DatabaseHelper.DatabaseListen("users", user.getEmail(), "XP", sharedPref);
		int progress = sharedPref.getInt("XP", 0);
		if (progress != 0) {
			String level = Integer.toString(ProgressHelper.levelCounter(progress));
			int max = ProgressHelper.getMaxExperience(Integer.parseInt(level));
			mLVLTxt.setText(level);
			mProgressBar.setMax(max);
			mProgressBar.setProgress(progress, true);
		}

		mProgressBar.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
//		        int progress = ProgressHelper.getProgress();
//		        mProgressBar.setProgress(progress);
	        }
        });


	    mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainMenuActivity.this, "You have been logout!!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainMenuActivity.this, LoginActivity.class));
            }

        });
        mSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, SettingsActivity.class));
                finish();
            }
        });

        mLibraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, LibraryThemeListActivity.class));
                finish();
            }
        });

        mProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, ProfileActivity.class));
                finish();
            }
        });

        mProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
                uploadFile();
            }
        });
    }

	private void openFileChooser() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent, PICK_IMAGE_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
			mPrifileUri = data.getData();
			mProfileImg.setImageURI(mPrifileUri);
		}
		;
	}

	private String getFileExntension(Uri uri) {
		ContentResolver cR = getContentResolver();
		MimeTypeMap mime = MimeTypeMap.getSingleton();
		return mime.getExtensionFromMimeType(cR.getType(uri));
	}

	private void uploadFile() {
		if (mPrifileUri != null) {
			StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
					+ "." + getFileExntension(mPrifileUri));

			fileReference.putFile(mPrifileUri)
					.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
						@Override
						public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

							Upload upload = new Upload(mUsername.getText().toString().trim(),
									taskSnapshot.getUploadSessionUri().toString());
							String uploadID = mDatabaseRef.push().getKey();
							mDatabaseRef.child(uploadID).setValue(upload);

						}
					})
					.addOnFailureListener(new OnFailureListener() {
						@Override
						public void onFailure(@NonNull Exception e) {

						}
					});
		} else {
			Toast.makeText(this, "No file selected1", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
    public void onBackPressed() {
        finishAndRemoveTask();
    }

    @Override
	public void onResume(){
    	super.onResume();

    }
}
