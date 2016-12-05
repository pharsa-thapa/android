package com.suresh.androidapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.suresh.androidapp.views.ContactListView;
import com.suresh.androidapp.R;
import com.suresh.androidapp.asyncTasks.ContactAsyncTask;
import com.suresh.androidapp.presenters.interfaces.ContactListPresenterInterface;


public class ContactListFragment extends MvpFragment<ContactListView, ContactListPresenterInterface> implements  ContactListView  {

    @Override
    public ContactListPresenterInterface createPresenter() {
        return new ContactListPresenterInterface() {
            @Override
            public void attachView(ContactListView view) {

            }

            @Override
            public void detachView(boolean retainInstance) {

            }
        };
    }

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
