package com.suresh.androidapp.presenters;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.suresh.androidapp.presenters.interfaces.FlowerDetailPresenterInterface;
import com.suresh.androidapp.views.FlowerDetailView;

/**
 * Created by suresh on 12/6/16.
 */

public class FlowerDetailPresenter implements MvpPresenter<FlowerDetailView>, FlowerDetailPresenterInterface {
    @Override
    public void attachView(FlowerDetailView view) {

    }

    @Override
    public void detachView(boolean retainInstance) {

    }
}
