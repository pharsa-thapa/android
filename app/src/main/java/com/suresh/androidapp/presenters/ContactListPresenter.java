package com.suresh.androidapp.presenters;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.suresh.androidapp.fragments.ContactListFragment;
import com.suresh.androidapp.presenters.interfaces.ContactListPresenterInterface;
import com.suresh.androidapp.views.ContactListView;

/**
 * Created by suresh on 11/29/16.
 */

public class ContactListPresenter implements ContactListPresenterInterface, MvpPresenter<ContactListView> {

    ContactListView contactListView;

    public ContactListPresenter(ContactListView contactListView) {
        this.contactListView = contactListView;
    }

    @Override
    public void attachView(ContactListView view) {

    }

    @Override
    public void detachView(boolean retainInstance) {

    }
}
