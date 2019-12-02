package com.example.learnest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.learnest.MainActivity;
import com.example.learnest.registrationActivity;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "login.db";


    public DatabaseHelper(registrationActivity regcontext) {
        super(regcontext, DATABASE_NAME, null, 1);
    }

    public DatabaseHelper(MainActivity logincontext) {
        super(logincontext, DATABASE_NAME, null, 1);
    }

    public DatabaseHelper(updatePassword updateContext) {
        super(updateContext, DATABASE_NAME, null, 1);
    }

    public DatabaseHelper(deleteAccount deleteAccountContext) {
        super(deleteAccountContext, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
    }

    public boolean Insert(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = sqLiteDatabase.insert("user", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean update(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        //Cursor cursor = sqLiteDatabase.rawQuery("UPDATE user SET password=? WHERE username=?",new String[]{password,username});
        long result = sqLiteDatabase.update("user",contentValues,"username=?",new String[]{username});
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean delete(String username){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //Cursor cursor = sqLiteDatabase.rawQuery("UPDATE user SET password=? WHERE username=?",new String[]{password,username});
        long result = sqLiteDatabase.delete("user","username=?",new String[]{username});
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean CheckUsername(String username){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE username=?", new String[]{username});
        if(cursor.getCount() > 0){
            return false;
        }else{
            return true;
        }
    }

    public Boolean CheckLogin(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE username=? AND password=?", new String[]{username, password});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}