package com.example.digitaleye;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "my_db";
    private static final String TABLE_NAME = "currecy_table";
    private static final String P_KEY = "ID";
    private static final String CURRENCY_WORTH = "currency_worth";
    private static final String TIMESTAMP = "timestamp";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String createTable = "CREATE TABLE " + TABLE_NAME + " (" +P_KEY+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + CURRENCY_WORTH + " TEXT,"
                    + TIMESTAMP + " TEXT"
                    + ")";
            db.execSQL(createTable);
        }catch (SQLiteException e)
        {
            try {
                throw new IOException(e);
            }catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item1 ,String item2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CURRENCY_WORTH, item1);
        contentValues.put(TIMESTAMP, item2);

        Log.d(TAG, "addData: Adding " + item1 + "&" + item2 + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public boolean cleardata() {
        SQLiteDatabase db = this.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
        long result = db.delete(TABLE_NAME, null, null);
        if (result == -1)
        {
            return false;
        }else
            {
            return true;
        }
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
  /*  public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }*/


    /**
     * Delete from database
     * @param id
     * @param name
     */
  /*  public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }*/
  /*  {
        try {
            String createTable = "CREATE TABLE " + TABLE_NAME + " (" +P_KEY+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + CURRENCY_WORTH + " TEXT,"
                    + TIMESTAMP + " TEXT"
                    + ")";
            db.execSQL(createTable);
        }catch (SQLiteException e)
        {
            try {
                throw new IOException(e);
            }catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
    }*/

}
