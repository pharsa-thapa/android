package com.suresh.androidapp.views;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by suresh on 12/12/16.
 */

public interface CategoryAddView extends MvpView {

    public void showSuccessfulAlert(Context ctx, String message);

    public void showFailureAlert(Context ctx, String message);
}
