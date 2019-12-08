package com.example.javalearner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;

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

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an Image"), PICK_IMAGE_REQUEST);
    }

    private void uploadFile(){
        StorageReference riversRef = mStorageReference.child("images/profileava.jpg");

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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mStorageReference = FirebaseStorage.getInstance().getReference();
        mLogoutBtn = findViewById(R.id.LogoutBtn);
        mUsername = findViewById(R.id.Username);
        mProgressBar = findViewById(R.id.progressBar);
        mLVLTxt = findViewById(R.id.LVLTxt);
        mSettingsBtn = findViewById(R.id.SettingsBtn);
        mProfileImg = findViewById(R.id.ProfileImg);
        mLibraryBtn = findViewById(R.id.LibraryBtn);
        mProfileBtn = findViewById(R.id.ProfileBtn);
        mStorageReference = FirebaseStorage.getInstance().getReference();


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
//                        }
                    }
                });
                mSettingsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainMenuActivity.this, SettingsActivity.class));
                    }
                });

                mLibraryBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainMenuActivity.this, LibraryThemeListActivity.class));
                    }
                });

                mProfileBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        uploadFile();
//                        startActivity(new Intent(MainMenuActivity.this, ProfileActivity.class));
                    }
                });

                mProfileImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openFileChooser();

                    }
                });
            }
        });
    }


}


