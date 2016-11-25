package com.suresh.androidapp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.suresh.androidapp.constants.DatabaseConstants;
import com.suresh.androidapp.entities.Contact;
import com.suresh.androidapp.helpers.DatabaseHelper;


/**
 * Created by suresh on 11/11/16.
 */

public class ContactModel {

    static Context context;
    private static String schema = DatabaseConstants.TABLE_CONTACTS;
    private static String primaryKeyName = DatabaseConstants.CONTACT_ID;

    public ContactModel(Context ctx) {
        context = ctx;
    }

    public static Long saveContact(Contact contact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConstants.CONTACT_NAME, contact.getContactName());
        contentValues.put(DatabaseConstants.CONTACT_NUMBER, contact.getContactNumber());

        return new DatabaseHelper(context).saveRecord(schema, contentValues);
    }

    public Cursor getAllContacts(){
        return new DatabaseHelper(context).getRecords(schema);
    }

    public static boolean deleteContact(Contact contact){
        int contactId = contact.getContactId();
        boolean result;
        result = new DatabaseHelper(context).deleteRecord(schema, primaryKeyName, contactId);

        return result;
    }
}