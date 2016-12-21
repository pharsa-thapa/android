package com.suresh.androidapp.interactors;

import android.content.Context;
import android.util.Log;

import com.suresh.androidapp.entities.Category;
import com.suresh.androidapp.entities.Contact;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by suresh on 12/12/16.
 */

public class CategoryInteractor {

    private Context context;
    private static Realm realm;

    public CategoryInteractor(Context ctx) {
        context = ctx;
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    public static Long saveCategory(Category category) {
        try {
            realm.beginTransaction();

            long lastId = (realm.where(Category.class).max("categoryId")) != null ? (long) (realm.where(Category.class).max("categoryId")) : 0;
            long nextID = (lastId < 1) ? 1 : lastId + 1;
            System.out.println("Next Id : "+ nextID);

            Category unsavedCategory = realm.createObject(Category.class, nextID);
            unsavedCategory.setCategoryName(category.getCategoryName());

            realm.copyToRealm(unsavedCategory);
            realm.commitTransaction();
            Log.v("CategoryInteractor", "Saved category : " + unsavedCategory.getCategoryName());
            return Long.parseLong(1 + "");
        } catch (Exception e) {
            Log.v("CategoryInteractor", "Failed to save category due to : " + e);
            return Long.parseLong(0 + "");
        }
    }

    public RealmResults<Category> getAllCategories() {
        RealmResults<Category> categories = realm.where(Category.class)
                .findAllSorted("categoryName", Sort.ASCENDING);
        return categories;
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
