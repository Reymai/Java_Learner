package com.example.javalearner;

import android.content.SharedPreferences;

public class SharedPreferencesHelper {
	private static SharedPreferences.Editor editor;

	public static void writeInt(String name, int preference, SharedPreferences sharedPref){
		editor = sharedPref.edit();
		editor.putInt(name, preference);
		editor.apply();
	}
	public static void writeString(String name, String preference, SharedPreferences sharedPref){
		editor = sharedPref.edit();
		editor.putString(name, preference);
		editor.apply();
	}
	public static void writeBoolean(String name, boolean preference, SharedPreferences sharedPref){
		editor = sharedPref.edit();
		editor.putBoolean(name, preference);
		editor.apply();
	}
}
