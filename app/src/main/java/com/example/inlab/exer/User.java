package com.example.inlab.exer;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by inlab on 29/01/2016.
 */
public class User {

    private String userName;
    private String homeTown;
    private String birthPlace;

    private static UsersHelper dbhelp;

    public User(String userName, String homeTown, String birthPlace) {
        this.userName = userName;
        this.homeTown = homeTown;
        this.birthPlace = birthPlace;
    }

    public void save() {
        ContentValues cv = new ContentValues();
        cv.put("user_name", userName);
        cv.put("hometown", homeTown);
        cv.put("birthplace", birthPlace);
        dbhelp.createUser(cv);
    }

    public static User read(String userName) {
        Cursor c = dbhelp.read(userName);
        if (c.moveToFirst()) {
            return new User(c.getString(c.getColumnIndex("user_name")),
                            c.getString(c.getColumnIndex("hometown")),
                            c.getString(c.getColumnIndex("birthplace")));
        }
        return null;
    }

    public static ArrayList<User> readAll() {
        ArrayList<User> retVal = new ArrayList<>();
        Cursor c = dbhelp.readAll();
        if (c.moveToFirst()) {
            do {
                retVal.add(new User(c.getString(c.getColumnIndex("user_name")),
                                    c.getString(c.getColumnIndex("hometown")),
                                    c.getString(c.getColumnIndex("birthplace"))));
            } while (c.moveToNext());
        }
        return retVal;
    }
}
