package com.thedomain.koindemonstration;

import android.content.Context;
import android.content.SharedPreferences;

import com.thedomain.koindemonstration.koin.User;

public class DataManager {

    private Context context;
    private static DataManager instance = null;
    private SharedPreferences prefs;

    public DataManager() {
    }

    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
        setPrefs();
    }

    public void setPrefs() {
        prefs = context.getSharedPreferences("demonstration_key", Context.MODE_PRIVATE);
    }

    public User getCurrentUser() {
        User userCurrent;
        userCurrent = new User(
                prefs.getString(context.getString(R.string.display_name), "N/A"),
                prefs.getString(context.getString(R.string.first_name), "N/A"),
                prefs.getInt(context.getString(R.string.user_id), 0)
        );
        return userCurrent;
    }

    public void setCurrentUser(User currentUser) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(context.getString(R.string.display_name), currentUser.getDisplayName());
        editor.putString(context.getString(R.string.first_name), currentUser.getFirstName());
        editor.putInt(context.getString(R.string.user_id), currentUser.getUserId());
        editor.apply();
    }

    public void clearUser(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }
}
