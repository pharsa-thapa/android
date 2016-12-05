package com.suresh.androidapp.presenters;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.suresh.androidapp.presenters.interfaces.ContactAddPresenterInterface;
import com.suresh.androidapp.views.ContactAddView;

/**
 * Created by suresh on 11/30/16.
 */
public class ContactAddPresenter implements ContactAddPresenterInterface, MvpPresenter<ContactAddView> {

    @Override
    public void attachView(ContactAddView view) {

    }

    @Override
    public void detachView(boolean retainInstance) {

    }
}
