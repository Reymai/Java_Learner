package com.example.javalearner;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public abstract class LocaleHelper extends Context {
	public static void changeLanguage(Locale locale, Context context){
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext(context).getResources().updateConfiguration(config, getBaseContext(context).getResources().getDisplayMetrics());
	}
	public static void changeLanguage(String language, Context context){
		Locale locale = new Locale(language);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext(context).getResources().updateConfiguration(config, getBaseContext(context).getResources().getDisplayMetrics());
	}

	private static Context getBaseContext(Context mBase) {
		return mBase;
	}

	public static boolean checkLocaleDatabase(String dbLang, Context context, int requestTime){
		try {
			Thread.sleep(requestTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!dbLang.equals("ru") && !dbLang.equals("en") && !dbLang.equals("lv")) {
			Log.e("Isn't actual", ""+dbLang);
			return false;
		}
		Locale locale = Locale.getDefault();
		final String lang = locale.getLanguage();

		Log.i("Locale:", "DB lang: "+dbLang + "\n Default lang: "+lang);

		if (!dbLang.equals(lang)) {
			locale = new Locale(dbLang);
			changeLanguage(locale, context);
			Log.e("Change Lang", ""+lang);
			return false;
		}
		return true;
	}
	public static boolean checkLocaleDatabase(FirebaseUser user, int requestTime, Context context){
		final String dbLang = DatabaseHelper.DatabaseRead("users", user.getEmail(), "Language");
		try {
			Thread.sleep(requestTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!dbLang.equals("ru") && !dbLang.equals("en") && !dbLang.equals("lv")) {
			Log.e("Isn't actual DB", ""+dbLang);
			return false;
		}
		Locale locale = Locale.getDefault();
		final String lang = locale.getLanguage();

		Log.i("Locale:", "DB lang: "+dbLang + "\n Default lang: "+lang);

		if (!dbLang.equals(lang)) {
			locale = new Locale(dbLang);
			changeLanguage(locale, context);
			Log.e("Change Lang", ""+lang);
			return false;
		}
		return true;
	}
	public static String getLocaleDatabase(FirebaseUser user, int requestTime){
		final String dbLang = DatabaseHelper.DatabaseRead("users", user.getEmail(), "Language");
		try {
			Thread.sleep(requestTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return dbLang;
	}

	@RequiresApi(api = Build.VERSION_CODES.N)
	public  static boolean checkLocaleSharedPreferences(FirebaseUser user, String key, SharedPreferences sharedPref, Context context){
		String language = sharedPref.getString(key, null);
		Locale locale = Locale.getDefault();

		final String defaultLanguage = locale.getLanguage();

		if (language == null){
			language = getLocaleDatabase(user, 300);
		}

		if (!language.equals("ru") && !language.equals("en") && !language.equals("lv")) {
			Log.e("Isn't actual SP", ""+language);
			return false;
		}

		Log.i("Locale:", "SharedPreferences: "+language + "\n Default lang: "+defaultLanguage);

		if (!language.equals(defaultLanguage)) {
			locale = new Locale(language);
			changeLanguage(locale, context);
			Log.e("Change language to: ", ""+language);

			SharedPreferences.Editor editor = sharedPref.edit();
			editor.putString("Locale", language);
			editor.commit();
			return false;
		}
		return true;
	}
}
