package com.suresh.androidapp.presenters.interfaces;

import android.app.Fragment;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.suresh.androidapp.fragments.ContactListFragment;

/**
 * Created by suresh on 11/30/16.
 */

public interface MainPresenterInterface {

    void replaceToListFragment();

    void replaceFragment(MvpFragment fragment);
}
