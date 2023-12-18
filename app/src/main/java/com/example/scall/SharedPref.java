package com.example.scall;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Type;
import java.util.List;

public class SharedPref {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_CONTACTS = "contacts";

    public static void saveContacts(Context context, List<ContactModel> contacts) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String jsonContacts = gson.toJson(contacts);
        editor.putString(KEY_CONTACTS, jsonContacts);
        editor.apply();
    }

    public static List<ContactModel> getContacts(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String jsonContacts = prefs.getString(KEY_CONTACTS, null);
        Type type = new TypeToken<List<ContactModel>>() {}.getType();
        return new Gson().fromJson(jsonContacts, type);
    }
}

