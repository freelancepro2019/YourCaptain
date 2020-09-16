package com.your_captain.preferences;

import android.content.Context;
import android.content.SharedPreferences;


import com.your_captain.models.UserModel;
import com.your_captain.tags.Tags;
import com.google.gson.Gson;

import java.util.Locale;

public class Preferences {

    private static Preferences instance = null;

    private Preferences() {
    }

    public static Preferences getInstance() {
        if (instance == null) {
            instance = new Preferences();
        }
        return instance;
    }

    public void create_update_language(Context context, String lang) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("language", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lang", lang);
        editor.apply();


    }

    public String getLanguage(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("language", Context.MODE_PRIVATE);
        return preferences.getString("lang", Locale.getDefault().getLanguage());

    }

    public void setIsLanguageSelected(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("language_selected", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("selected", true);
        editor.apply();
    }

    public boolean isLanguageSelected(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("language_selected", Context.MODE_PRIVATE);
        return preferences.getBoolean("selected", false);
    }

   public void create_update_userdata(Context context, UserModel userModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String user_data = gson.toJson(userModel);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_data", user_data);
        editor.apply();
        create_update_session(context, Tags.session_login);

    }

  public UserModel getUserData(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String user_data = preferences.getString("user_data", "");
        UserModel userModel = gson.fromJson(user_data, UserModel.class);
        return userModel;
    }
    public void create_update_session(Context context, String session) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("state", session);
        editor.apply();


    }


    public void create_room_id(Context context, String room_id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("room", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("room_id", room_id);
        editor.apply();


    }

    public String getRoomId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("room", Context.MODE_PRIVATE);
        String room_id = preferences.getString("room_id", Tags.session_logout);
        return room_id;
    }

    public String getSession(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        String session = preferences.getString("state", Tags.session_logout);
        return session;
    }

    public void setLastVisit(Context context,String date)
    {
        SharedPreferences preferences = context.getSharedPreferences("visit",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("lastVisit",date);
        editor.apply();

    }
    public String getLastVisit(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences("visit",Context.MODE_PRIVATE);
        return preferences.getString("lastVisit","0");
    }
    public void clear(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.clear();
        edit.apply();
        SharedPreferences preferences2 = context.getSharedPreferences("room", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit2 = preferences2.edit();
        edit2.clear();
        edit2.apply();
        create_update_session(context, Tags.session_logout);
    }



}
