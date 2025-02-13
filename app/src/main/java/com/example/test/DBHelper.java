package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDatabase.db";
    public static final String TABLE_NAME = "my_table";
    public static final String COL_ENROLL = "ENROLL";
    public static final String COL_FNAME = "FNAME";
    public static final String COL_LNAME = "LNAME";
    public static final String COL_MNAME = "MNAME";
    public static final String EMAIl = "EMAIL";
    public static final String CLASSES= "CLASS";
    public static final String GENDER= "GENDER";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ENROLL + " TEXT PRIMARY KEY," +
                COL_FNAME + " TEXT," +
                COL_MNAME + " TEXT," +
                COL_LNAME + " TEXT," +
                GENDER + " TEXT," +
                EMAIl + " TEXT," +
                CLASSES + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table if exists and recreate
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String fname, String mname, String lname, String gender, String email ,String classes , String roll) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ENROLL , roll);
        contentValues.put(COL_FNAME, fname);
        contentValues.put(COL_MNAME, mname);
        contentValues.put(COL_LNAME, lname);
        contentValues.put(GENDER, gender);
        contentValues.put(EMAIl, email);
        contentValues.put(CLASSES , classes);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public boolean updateData(String fname, String mname, String lname, String gender, String email ,String classes , String roll) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_FNAME, fname);
        contentValues.put(COL_MNAME, mname);
        contentValues.put(COL_LNAME, lname);
        contentValues.put(GENDER, gender);
        contentValues.put(EMAIl, email);
        contentValues.put(CLASSES , classes);
        int rowsAffected = db.update(TABLE_NAME, contentValues, "ENROLL=?", new String[]{roll});
        return rowsAffected > 0;
    }

    public boolean deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_NAME, "ENROLL=?", new String[]{id});
        return rowsDeleted > 0;
    }

}
