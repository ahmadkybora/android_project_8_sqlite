package com.example.android_project_8_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

public class SQLiteHelper {
    private final static String DIR_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
    private final static String DIR_DATABASE = DIR_SDCARD + "/Android/data/com.example.android_project_8_sqlite/files";
    private final static String DATABASE_NAME = "mySqlite";
    private static SQLiteHelper sqLiteHelper;
    private static SQLiteDatabase database;
    private Person person;

    private SQLiteHelper() {

    }

    public static SQLiteHelper builder() {
        if (sqLiteHelper == null) {
            sqLiteHelper = new SQLiteHelper();
            openOrCreateDatabase();
        }
        return sqLiteHelper;
    }

    private static void openOrCreateDatabase() {
        File file = new File(DIR_DATABASE);
        file.mkdirs();

        database = SQLiteDatabase.openOrCreateDatabase(DIR_DATABASE + "/" + DATABASE_NAME, null);
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    private void createPersonTable() {
        try {
            getDatabase().execSQL(
                    "CREATE TABLE IF NOT EXISTS persons (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "first_name TEXT, " +
                            "last_name TEXT, " +
                            "mobile TEXT)"
            );
        } catch (SecurityException e) {

        }
    }

    public void insertPersonTable(Person person) {
        try {
            getDatabase().execSQL(
                    "INSERT INTO persons (" +
                            "id , " +
                            "first_name, " +
                            "last_name, " +
                            "mobile " +
                            ") VALUES (" +
                            "'" + person.getFirstname() + "', " +
                            "'" + person.getLastname() + "', " +
                            "'" + person.getMobile() + "')" +
                            "");

            /*ContentValues contentValues = new ContentValues();

            contentValues.put("first_name", person.getFirstname());
            contentValues.put("last_name", person.getLastname());
            contentValues.put("mobile", person.getMobile());
            getDatabase().insert("person", null, contentValues);*/
        } catch (SecurityException e) {

        }
    }

    public ArrayList<Person> getPersons() {
        ArrayList<Person> personArrayList = new ArrayList<>();
        Cursor cursor = getDatabase().rawQuery("SELECT * FROM person", null);
        if (cursor == null) {
            return personArrayList;
        }

        while (cursor.moveToNext()) {
            Person person = new Person();
            person.setFirst_name(cursor.getString(cursor.getColumnIndex("first_name")));
            person.setLast_name(cursor.getString(cursor.getColumnIndex("last_name")));
            person.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));

            personArrayList.add(person);
        }

        cursor.close();
        return personArrayList;
    }

    public Person getPerson(String first_name) {
        Person person = null;
        Cursor cursor = getDatabase().rawQuery("SELECT * FROM person WHERE mobile = '" + first_name + "'", null);

        if (cursor != null && cursor.moveToFirst()) {
            person = new Person();
            person.setFirst_name(cursor.getString(cursor.getColumnIndex("first_name")));
            person.setLast_name(cursor.getString(cursor.getColumnIndex("last_name")));
            person.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
        }
        return person;
    }
}
