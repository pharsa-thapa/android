package com.suresh.androidapp.presenters.interfaces;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.suresh.androidapp.entities.Contact;
import com.suresh.androidapp.views.ContactAddView;

/**
 * Created by suresh on 11/30/16.
 */

public interface ContactAddPresenterInterface extends MvpPresenter<ContactAddView> {

    void proceedSave(Contact rawContact);

}
