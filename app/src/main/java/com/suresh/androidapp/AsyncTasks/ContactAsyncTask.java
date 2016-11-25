package com.suresh.androidapp.AsyncTasks;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.suresh.androidapp.R;
import com.suresh.androidapp.adapters.ContactRVAdapter;
import com.suresh.androidapp.entities.Contact;
import com.suresh.androidapp.models.ContactModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suresh on 11/20/16.
 */

public class ContactAsyncTask extends AsyncTask<String, Void, List<Contact>> {

    private View view;
    private Context context;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public ContactAsyncTask(View vw, Context ctx, ProgressBar pgrBar) {
        view = vw;
        context = ctx;
        progressBar = pgrBar;
        recyclerView = (RecyclerView) view.findViewById(R.id.contact_recycler_view);
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    protected List<Contact> doInBackground(String... urls) {
        ContactModel contactModel = new ContactModel(context);
        Cursor cursor = contactModel.getAllContacts();

        List<Contact> contactList = new ArrayList<>();

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Contact contact = new Contact();
                contact.setContactId(cursor.getInt(0));
                contact.setContactName(cursor.getString(1));
                contact.setContactNumber(cursor.getString(2));

                contactList.add(contact);
            }
        }


        return contactList;
    }

    @Override
    protected void onPostExecute(List<Contact> contactList) {
        progressBar.setVisibility(View.INVISIBLE);

        ContactRVAdapter adapter = new ContactRVAdapter(contactList, context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
}
