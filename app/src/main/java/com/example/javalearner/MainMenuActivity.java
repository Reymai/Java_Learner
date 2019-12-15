package com.example.javalearner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Locale;

public class MainMenuActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 234;

    ImageButton mLogoutBtn;
    ImageButton mSettingsBtn;
    ImageButton mLibraryBtn;
    ImageButton mProfileBtn;
    TextView mUsername;
    TextView mLVLTxt;
    ProgressBar mProgressBar;
    ImageView mProfileImg;
    StorageReference mStorageReference;
    Uri filePath;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction ;

	private FirebaseAuth mAuth;
	private FirebaseUser mUser;
	private StorageReference mStorageRef;
	private DatabaseReference mDatabaseRef;
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

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
        mStorageReference = FirebaseStorage.getInstance().getReference();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        ContainerFragments quests = new ContainerFragments();

        fragmentTransaction.replace(R.id.container,quests);
        fragmentTransaction.commit();

        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainMenuActivity.this, "You have been logout!!!", Toast.LENGTH_SHORT);
                startActivity(new Intent(MainMenuActivity.this, LoginActivity.class));
            }

        });


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
		if (!dbLang.equals("null")){
			SharedPreferencesHelper.WriteToSharedPreferences("Locale", locale.getLanguage(), sharedPref);
		}


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
		int progress = 0;
		try {
			progress = Integer.parseInt(sharedPref.getString("XP", "0"));
		}catch (Exception e){
			Log.e("PROGRESS", ""+e);
		}
		String level = Integer.toString(ProgressHelper.levelCounter(progress));
		int max = ProgressHelper.getMaxExperience(Integer.parseInt(level));
		mLVLTxt.setText(level);
		mProgressBar.setMax(max);
		mProgressBar.setProgress(progress, true);

		//download avatar



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
//                        uploadFile();
                        startActivity(new Intent(MainMenuActivity.this, ProfileActivity.class));
                    }
                });

                mProfileImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openFileChooser();

                    }
                });
		}

	private void openFileChooser(){
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select an Image"), PICK_IMAGE_REQUEST);
	}

	private void uploadFile(){
		StorageReference riversRef = mStorageReference.child("Avatars/" + user.getEmail().toString());
		riversRef.putFile(filePath)
				.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
					@Override
					public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception exception) {
						// Handle unsuccessful uploads
						// ...
					}
				});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
			filePath = data.getData();
			try{
				Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
				mProfileImg.setImageBitmap(bitmap);
				uploadFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
