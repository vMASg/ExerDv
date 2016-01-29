package com.example.inlab.exer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by inlab on 29/01/2016.
 */
public class UsersHelper extends SQLiteOpenHelper {

    //Declaracion del nombre de la base de datos
    public static final int DATABASE_VERSION = 1;

    //Declaracion global de la version de la base de datos
    public static final String DATABASE_NAME = "AppDB";

    //Declaracion del nombre de la tabla
    public static final String TABLE_NAME ="Users";

    //sentencia global de cracion de la base de datos
    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (user_name VARCHAR(255) PRIMARY KEY, hometown TEXT, birthplace TEXT);";

    public UsersHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    public void createUser(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(
                TABLE_NAME,
                null,
                values
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor read(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] value = {userName};
        return db.query(TABLE_NAME, null, "user_name=?", value, null, null, null);
    }

    public Cursor readAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }
}
