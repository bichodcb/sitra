package com.example.lenovo.sitra.card;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class Storage {
	private static final String PREF_ACCOUNT_NUMBER = "account_number";
	private static final String DEFAULT_ACCOUNT_NUMBER = "00000000";
	private static final String TAG = "Storage";
	private static String sAccount = null;
	private static final Object sAccountLock = new Object();
	
	public static void SetAccount(Context c, String s) {
		synchronized(sAccountLock) {
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
			prefs.edit().putString(PREF_ACCOUNT_NUMBER, s).commit();
			sAccount = s;
		}
	}
	
	public static String GetAccount(Context c) {
		synchronized (sAccountLock) {
			if (sAccount == null) {
				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
				String account = prefs.getString(PREF_ACCOUNT_NUMBER, DEFAULT_ACCOUNT_NUMBER);
				sAccount = account;
			}
			return sAccount;
		}
	}
}
