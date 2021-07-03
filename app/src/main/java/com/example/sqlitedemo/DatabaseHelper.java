package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement ="CREATE TABLE user (nic TEXT PRIMARY KEY, name TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("nic", userModel.getNic());
        cv.put("name", userModel.getName());

        long insert = db.insert("user", null, cv);
        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteOne(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM user WHERE nic = "+"\'" + userModel.getNic() +"\'";

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public List<UserModel> getUsers() {

        List<UserModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM user";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            do {
                String userNic = cursor.getString(0);
                String userName = cursor.getString(1);

                UserModel userModel = new UserModel(userNic, userName);
                returnList.add(userModel);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }


}
