package com.suresh.androidapp.presenters.interfaces;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.suresh.androidapp.entities.Category;
import com.suresh.androidapp.views.CategoryAddView;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by suresh on 12/12/16.
 */

public interface CategoryAddPresenterInterface extends MvpPresenter<CategoryAddView> {

    void addCategory(String categoryName, Context context);

    List<Category> getAllCategories(Context context);
}
