package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String USERS = "users";
    public static final String USERNAME = "username";
    public static final String AGE = "age";
    public static final String EMAIL = "email";
    public static final String ID = "id";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "testuser.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable =
                "CREATE TABLE " + USERS + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT, " + AGE + " INTEGER, " + EMAIL + " TEXT)";

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS " + USERS;
        db.execSQL(dropTable);
        onCreate(db);

    }

    public boolean adduser(DataModel dataModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USERNAME, dataModel.getName());
        cv.put(AGE, dataModel.getAge());
        cv.put(EMAIL, dataModel.getEmail());

        long result = db.insert(USERS, null, cv);
        if(result == -1){
            return false;
        }else{
            return true;
        }

    }

    public List<DataModel> getAllUsers(){
        List<DataModel> outputList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USERS;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String email = cursor.getString(3);

                DataModel dataModel = new DataModel(name, age, email);
                outputList.add(dataModel);

            }while(cursor.moveToNext());

            }else {
        }

        cursor.close();
        db.close();

        return outputList;
    }

    public boolean deleteUser(DataModel dataModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + USERS + " WHERE " + EMAIL + " = '" + dataModel.getEmail() + "'";

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            return false;
        }else{
            return true;
        }

    }
}

