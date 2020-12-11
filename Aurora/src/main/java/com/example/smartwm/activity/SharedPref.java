package com.example.smartwm.activity;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPref {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private String key_mobile="mobile";
    private  String key_password="password";
    private  String key_username="username";
    private final int mode = MODE_PRIVATE;

    public SharedPref(Context context) {
        pref = context.getSharedPreferences("user_detail",mode);
        editor = pref.edit();
        editor.apply();
    }

    public String getMobile(){
        return pref.getString(key_mobile,"");
    }
    public void setMobile(String mobile){
        editor.putString(this.key_mobile,mobile);
        editor.commit();
    }

    public  String getPassword(){
        return pref.getString(key_password,"");
    }
    public void  setPassword(String password){
        editor.putString(this.key_password,password);
        editor.commit();
    }

    public  String getUsername(){
        return pref.getString(key_username,"");
    }

    public  void setUsername(String username){
        editor.putString(this.key_username,username);
        editor.commit();
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }
}