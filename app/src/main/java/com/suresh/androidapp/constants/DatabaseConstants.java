package com.suresh.androidapp.constants;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by suresh on 11/11/16.
 */

public class DatabaseConstants {

    public static final String DATABASE_NAME = "android_contacts_db.db";
    public static final SQLiteDatabase.CursorFactory DATABASE_FACTORY = null;
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_CONTACTS = "tbl_contacts";
    public static final String CONTACT_ID ="contact_id";
    public static final String CONTACT_NAME = "contact_name";
    public static final String CONTACT_NUMBER = "contact_number";

    public static final String CONTACT_SCHEMA = "CREATE TABLE "
            + TABLE_CONTACTS + "(" + CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CONTACT_NAME
            + " VARCHAR(20)," + CONTACT_NUMBER + " VARCHAR(20)" + ")";

    public static final String DROP_CONTACT_SCHEMA = "DROP TABLE IF EXISTS " + TABLE_CONTACTS;




}
