package com.example.javalearner;

import android.content.SharedPreferences;

public class SharedPreferencesHelper {
	private static SharedPreferences.Editor editor;

	private static void writeInt(String name, int preference, SharedPreferences sharedPref){
		editor = sharedPref.edit();
		editor.putInt(name, preference);
		editor.apply();
	}
	public static void writeString(String name, String preference, SharedPreferences sharedPref){
		editor = sharedPref.edit();
		editor.putString(name, preference);
		editor.apply();
	}
	private static void writeBoolean(String name, boolean preference, SharedPreferences sharedPref){
		editor = sharedPref.edit();
		editor.putBoolean(name, preference);
		editor.apply();
	}

	public static void WriteToSharedPreferences(String name, Object fieldData, SharedPreferences sharedPref){
		int dataInteger;
		String dataString;
		boolean dataBoolean;
		try {
			dataInteger = (Integer) fieldData;
			SharedPreferencesHelper.writeInt(name, dataInteger, sharedPref);
		}catch (Exception exception){
			exception.printStackTrace();
		}
		try {
			dataString = String.valueOf(fieldData);
			SharedPreferencesHelper.writeString(name, dataString, sharedPref);

		}catch (Exception exception){
			exception.printStackTrace();
		}
		try {
			dataBoolean = (boolean)(fieldData);
			SharedPreferencesHelper.writeBoolean(name, dataBoolean, sharedPref);
		}catch (Exception exception){
			exception.printStackTrace();
		}
	}

}
