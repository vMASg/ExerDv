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
    private String password;

    public static UsersHelper dbhelp;

    public User(String userName, String homeTown, String birthPlace) {
        this.userName = userName;
        this.homeTown = homeTown;
        this.birthPlace = birthPlace;
        this.password = null;
    }

    public void setPassword (String pssw) {
        this.password = pssw;
    }

    public void save() {
        ContentValues cv = new ContentValues();
        cv.put("user_name", userName);
        cv.put("hometown", homeTown);
        cv.put("birthplace", birthPlace);
        cv.put("password", password);
        dbhelp.createUser(cv);
    }

    public static User read(String userName) {
        Cursor c = dbhelp.read(userName);
        if (c.moveToFirst()) {
            User user = new User(c.getString(c.getColumnIndex("user_name")),
                                 c.getString(c.getColumnIndex("hometown")),
                                 c.getString(c.getColumnIndex("birthplace")));
            user.password = c.getString(c.getColumnIndex("password"));
            return user;
        }
        return null;
    }

    public static ArrayList<User> readAll() {
        ArrayList<User> retVal = new ArrayList<>();
        Cursor c = dbhelp.readAll();
        if (c.moveToFirst()) {
            do {
                User user = new User(c.getString(c.getColumnIndex("user_name")),
                        c.getString(c.getColumnIndex("hometown")),
                        c.getString(c.getColumnIndex("birthplace")));
                user.password = c.getString(c.getColumnIndex("password"));
                retVal.add(user);
            } while (c.moveToNext());
        }
        return retVal;
    }

    public boolean comparePassword(String password) {
        return password.equals(this.password);
    }

    public String getUserName() {
        return userName;
    }

    public String getHometown() {
        return homeTown;
    }

    public String getBirthplace() {
        return birthPlace;
    }
}
