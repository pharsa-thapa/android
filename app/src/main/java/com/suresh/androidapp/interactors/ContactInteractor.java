package com.suresh.androidapp.interactors;

import android.content.Context;
import android.util.Log;

import com.suresh.androidapp.config.RealmConfig;
import com.suresh.androidapp.entities.Contact;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


/**
 * Created by suresh on 11/11/16.
 */

public class ContactInteractor {

    private Context context;
    private static Realm realm;

    public ContactInteractor(Context ctx) {
        context = ctx;
        Realm.init(ctx);
        realm = Realm.getDefaultInstance();
    }

    public static Long saveContact(Contact contact) {
        try {
            realm.beginTransaction();

            long lastId = (realm.where(Contact.class).max("contactId")) != null ? (long) (realm.where(Contact.class).max("contactId")) : 0;
            long nextID = (lastId < 1) ? 1 : lastId + 1;

            Contact unsavedContact = realm.createObject(Contact.class, nextID);
            unsavedContact.setContactNumber(contact.getContactNumber());
            unsavedContact.setContactName(contact.getContactName());

            realm.copyToRealm(unsavedContact);
            realm.commitTransaction();

            return Long.parseLong(1 + "");
        } catch (Exception e) {
            Log.v("ContactModel", "Failed to save contact due to : " + e);
            return Long.parseLong(0 + "");
        }
    }

    public RealmResults<Contact> getAllContacts() {
        RealmResults<Contact> contacts = realm.where(Contact.class)
                .findAllSorted("contactName", Sort.ASCENDING);
        return contacts;
    }

    public static boolean deleteContact(Contact contact) {
        final long contactId = contact.getContactId();

        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<Contact> contacts = realm.where(Contact.class).equalTo("contactId", contactId).findAll();
                    contacts.deleteAllFromRealm();
                }
            });

            return true;

        } catch (Exception e) {
            Log.v("ContactModel", "Failed to delete record due to : " + e);
            return false;
        }
    }
}