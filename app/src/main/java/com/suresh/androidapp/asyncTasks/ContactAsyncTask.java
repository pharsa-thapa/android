package com.suresh.androidapp.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.suresh.androidapp.R;
import com.suresh.androidapp.adapters.ContactRVAdapter;
import com.suresh.androidapp.entities.Contact;
import com.suresh.androidapp.interactors.ContactInteractor;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

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
        ContactInteractor contactModel = new ContactInteractor(context);
        RealmResults<Contact> contacts = contactModel.getAllContacts();

        List<Contact> contactList = new ArrayList<>();

        if (contactList != null) {
            for (Contact contact : contacts) {
                Contact contactInstance = new Contact();
                contactInstance.setContactId(contact.getContactId());
                contactInstance.setContactName(contact.getContactName());
                contactInstance.setContactNumber(contact.getContactNumber());

                contactList.add(contactInstance);
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
