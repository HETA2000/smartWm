package com.example.smartwm.database;
//Aurora

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "user", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = " CREATE TABLE  user(id INTEGER PRIMARY KEY AUTOINCREMENT ,name text,email text,contact text,password text )";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    //INSERTING INTO DB
    public boolean insertData(String name, String email, String contact, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("email", email);
        cv.put("contact", contact);
        cv.put("password", password);
        long result = db.insert("user", null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //CHECKING EMAIL EXIST
    public Boolean chkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("Select * from user where email=?", new String[]{email});
        if (result.getCount() > 0) {
            return false;
        } else return true;
    }

    //CHECKING EMAIL AND PASSWORD FOR LOGIN
    public Boolean Login(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM user WHERE email = ? and password = ?", new String[]{email, password});
        if (result.getCount() > 0) {
            return true;
        } else return false;
    }

}
