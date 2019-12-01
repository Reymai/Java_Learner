package com.example.javalearner;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
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

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mPrifileUri = data.getData();
            mProfileImg.setImageURI(mPrifileUri);
        };
    }

    private String getFileExntension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (mPrifileUri != null){
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
        }else {
            Toast.makeText(this, "No file selected1", Toast.LENGTH_SHORT).show();
        }
    }


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
                Toast.makeText(MainMenuActivity.this, "You have been logout!!!", Toast.LENGTH_SHORT);
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
            final SharedPreferences sharedPref = MainMenuActivity.this.getPreferences(Context.MODE_PRIVATE);
            final String dbLang = DatabaseHelper.DatabaseRead("users", user.getEmail(), "Language");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Locale locale = new Locale(dbLang);

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("Locale", locale.getLanguage());
            editor.commit();

            if (!LocaleHelper.checkLocaleSharedPreferences("Locale", sharedPref, this)){
                super.recreate();
            }
        //Progress
        mMainMenuPB.setVisibility(View.VISIBLE);
        try {
            int progress = Integer.parseInt(DatabaseHelper.DatabaseRead("users", user.getEmail(), "XP"));
            mMainMenuPB.setVisibility(View.GONE);
            mProgressBar.setMax(100);
            mProgressBar.setProgress(progress);
            Toast.makeText(MainMenuActivity.this, "Progress: "+progress, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e("Database error:", ""+e);
//            recreate();
        }

        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
        mProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("users").document(user.getEmail());
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        int progress = 0;
                        mProgressBar.setMax(100);
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                progress = Integer.parseInt(document.getData().get("XP").toString());
                            }
                        } else {
                            Toast.makeText(MainMenuActivity.this, "Some Error", Toast.LENGTH_SHORT);
                        }
                        mProgressBar.setProgress(progress);
                    }
                });


                mProgressBar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainMenuActivity.this, "+5 XP", Toast.LENGTH_SHORT).show();
                        int progress = 5;
                        int currentProgress;
                        int progressMax = mProgressBar.getMax();

//                String level = mLVLTxt.getText().toString();
//                int levelInt = Integer.valueOf(level);

                        mProgressBar.setMax(100);
                        currentProgress = mProgressBar.getProgress();
                        mProgressBar.setProgress(currentProgress + progress);
//                        if(currentProgress>=progressMax){
//                            mLVLTxt.setText(levelInt = levelInt + 1);
//                            mProgressBar.setProgress(0);
//                            mProgressBar.setMax(progressMax * 2);
//                        }            }
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
        });
    }
}


    @Override
    public void onBackPressed() {
        finishAndRemoveTask();
    }
}