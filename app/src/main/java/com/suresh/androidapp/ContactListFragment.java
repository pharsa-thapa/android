package com.suresh.androidapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import com.suresh.androidapp.AsyncTasks.ContactAsyncTask;


public class ContactListFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Contacts");
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        ContactAsyncTask contactAsyncTask = new ContactAsyncTask(view, getContext(), (ProgressBar) getActivity().findViewById(R.id.async_progress));
        contactAsyncTask.execute();

        return view;
    }
}
