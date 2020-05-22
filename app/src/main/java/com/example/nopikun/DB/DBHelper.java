package com.example.nopikun.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nopikun.Model.Password;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "PasswordDB";
    private static final int DB_VERSION = 1;

    public static final String TABLE_PASSWORD = "table_password";
    public static final String FIELD_PASSWORD_ID = "id";
    public static final String FIELD_PASSWORD_TITLE = "title";
    public static final String FIELD_PASSWORD_USERNAME = "username";
    public static final String FIELD_PASSWORD_PASSWORD = "password";


    private static final String CREATE_TABLE_ =
            "CREATE TABLE " + TABLE_PASSWORD + " (" +
                    FIELD_PASSWORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FIELD_PASSWORD_TITLE +" TEXT," +
                    FIELD_PASSWORD_USERNAME +" TEXT," +
                    FIELD_PASSWORD_PASSWORD +" TEXT)";

    public DBHelper(@Nullable  Context context) {
        super( context, DB_NAME, null, DB_VERSION );
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS table_password");
        onCreate( db );
    }

    public long insertInto(Password password){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues initValues = new ContentValues();
        initValues.put(FIELD_PASSWORD_TITLE,password.getTitle());
        initValues.put(FIELD_PASSWORD_USERNAME,password.getUsername());
        initValues.put(FIELD_PASSWORD_PASSWORD,password.getPassword());


        return db.insert(TABLE_PASSWORD,null,initValues);
    }

    public List<Password > savedPassword () {

        SQLiteDatabase db = this.getReadableDatabase();
        List<Password> list = new ArrayList<>();
        Cursor cr = db.rawQuery("select * from " + TABLE_PASSWORD, null);
        cr.moveToFirst();

        while (!cr.isAfterLast()) {
            int id = cr.getInt(cr.getColumnIndex(FIELD_PASSWORD_ID));
            String title = cr.getString(cr.getColumnIndex(FIELD_PASSWORD_TITLE));
            String username = cr.getString( cr.getColumnIndex( FIELD_PASSWORD_USERNAME ));
            String password = cr.getString(cr.getColumnIndex(FIELD_PASSWORD_PASSWORD));


            Password temp_password = new Password(title,username,password,id);
            list.add(temp_password);

            cr.moveToNext();

        }
        if (!cr.isClosed()) {
            cr.close();
        }
        return list;
    }

    public boolean deletePassword(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_PASSWORD, FIELD_PASSWORD_ID + "=" + id, null) > 0;
    }

}
