package com.suresh.androidapp.presenters;

import android.support.v7.app.AppCompatActivity;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.suresh.androidapp.R;
import com.suresh.androidapp.fragments.ContactListFragment;
import com.suresh.androidapp.presenters.interfaces.MainPresenterInterface;
import com.suresh.androidapp.views.MainView;

/**
 * Created by suresh on 11/30/16.
 */
public class MainPresenter extends MvpBasePresenter<MainView> implements MainPresenterInterface {

    @Override
    public void replaceToListFragment() {
        replaceFragment(new ContactListFragment());
    }

    @Override
    public void replaceFragment(MvpFragment fragment) {
        if (isViewAttached()) {
            ((AppCompatActivity) getView().getContext()).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, fragment)
                    .commitAllowingStateLoss();
        }
    }

}
