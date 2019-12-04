package com.example.javalearner;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper{

	private static DocumentSnapshot document;
	private static String result;
	private static int intResult;
	private static FirebaseFirestore db = FirebaseFirestore.getInstance();


	public  static void DatabaseListen(String collectionPath, String documentToSearch, final String field, SharedPreferences sharedPref){
		final DocumentReference docRef = db.collection(collectionPath).document(documentToSearch);

		docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
			@Override
			public void onEvent(@Nullable DocumentSnapshot snapshot,
			                    @Nullable FirebaseFirestoreException e) {
				if (e != null) {
					Log.w("DatabaseListener", "Listen failed.", e);
					return;
				}

				if (snapshot != null && snapshot.exists()) {
					Log.d("DatabaseListener", "Current data: " + snapshot.getData().get(field).toString());
				} else {
					Log.d("DatabaseListener", "Current data: null");
				}
				intResult = Integer.parseInt(snapshot.getData().get(field).toString());
			}
		});

		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt(field, intResult);
		editor.apply();
	}

	public static String DatabaseRead(String collectionPath, String documentToSearch, final String field) {
			final DocumentReference docRef = db.collection(collectionPath).document(documentToSearch);

			docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

				public void onComplete(@NonNull Task<DocumentSnapshot> task) {
					if (task.isSuccessful()) {
						document = task.getResult();

						if (document.exists()) {
							result = document.getData().get(field).toString();
							Log.i("DatabaseRead", "" + result);
						} else {
							Log.e("DatabaseRead", "Document is not exist!");
						}
					} else {
						Log.e("DatabaseRead", "Something has wrong with Database! " + task.getException());
					}
				}

			});
			if (result == null){
				return "null";
			}else{
				return result;
			}
	}
	public static String DatabaseRead(String collectionPath, String documentToSearch){
		final DocumentReference docRef = db.collection(collectionPath).document(documentToSearch);

		docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

			public void onComplete(@NonNull Task<DocumentSnapshot> task) {
				if (task.isSuccessful()) {
					document = task.getResult();
					if (document.exists()) {
						result = document.getData().toString();
						Log.i("DatabaseRead", "" + result);
					}else{
						Log.e("DatabaseRead","Document is not exist!");
					}
				} else {
					Log.e("DatabaseRead", "Something has wrong with Database! " + task.getException());
				}
			}

		});
		if(result != null) {
			return result;
		}else{
			return "0";
		}
	}

	public static void DatabaseWrite(String collectionPath, String documentName, Map<String, Object> user){
		db.collection(collectionPath).document(documentName)
				.set(user)
				.addOnSuccessListener(new OnSuccessListener<Void>() {
					@Override
					public void onSuccess(Void aVoid) {
						Log.i("DatabaseWrite", "Database has been successfully written");
					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						Log.e("DatabaseWrite", "Database doesn't written successfully");
					}
				});
	}

	public  static void DatabaseUpdateField(String collectionPath, String documentName, String fieldTitle, String fieldVariable){
		Map<String, Object> data = new HashMap<>();
		data.put(fieldTitle, fieldVariable);

		db.collection(collectionPath).document(documentName)
				.set(data, SetOptions.merge());
	}

}