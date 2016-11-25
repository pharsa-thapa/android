package com.suresh.androidapp.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.suresh.androidapp.constants.DatabaseConstants;


/**
 * Created by suresh on 11/11/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, DatabaseConstants.DATABASE_FACTORY, DatabaseConstants.DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseConstants.CONTACT_SCHEMA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DatabaseConstants.DROP_CONTACT_SCHEMA);
        onCreate(sqLiteDatabase);
    }

    public Long saveRecord(String schema, ContentValues contentValues) {
        return db.insert(schema, null, contentValues);
    }


    public Cursor getRecords(String schema) {
        String query = "SELECT ";
        query += " * ";
        query += " FROM ";
        query += schema;
        query += " WHERE ";
        query += " 1 ";
        query += " ORDER BY ";
        query += DatabaseConstants.CONTACT_NAME;
        query += " ASC";

        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public boolean deleteRecord(String schema, String primaryKeyString, int primaryKey) {
        return (db.delete(schema, primaryKeyString + " = " + primaryKey , null) > 0) ? true : false;
    }
}
