package com.suresh.androidapp.views;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by suresh on 11/30/16.
 */
public interface ContactAddView extends MvpView {

    void saveSuccessfulAlert(String successfulMessage);

    void saveFailureAlert(String failureMessage);

}
